/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.oauth.web.internal.security.auth;

import com.liferay.oauth.constants.OAuthApplicationConstants;
import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthApplicationLocalService;
import com.liferay.oauth.service.OAuthUserLocalService;
import com.liferay.oauth.util.DefaultOAuthAccessor;
import com.liferay.oauth.util.OAuth;
import com.liferay.oauth.util.OAuthAccessor;
import com.liferay.oauth.util.OAuthMessage;
import com.liferay.oauth.util.WebServerUtil;
import com.liferay.oauth.web.internal.service.access.policy.OAuthSAPEntryActivator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.security.service.access.policy.ServiceAccessPolicyThreadLocal;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import net.oauth.OAuthProblemException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Tomas Polesovsky
 */
@Component(
	immediate = true,
	property = {
		"auth.verifier.OAuthVerifier.send.body=true",
		"auth.verifier.OAuthVerifier.urls.includes=/api/*"
	},
	service = AuthVerifier.class
)
public class OAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return OAuthVerifier.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		HttpServletRequest httpServletRequest =
			accessControlContext.getRequest();

		if (!isUsingOAuth(httpServletRequest)) {
			return authVerifierResult;
		}

		try {
			OAuthMessage oAuthMessage = _oAuth.getOAuthMessage(
				httpServletRequest,
				WebServerUtil.getWebServerURL(
					httpServletRequest.getRequestURL()));

			OAuthUser oAuthUser = getOAuthUser(oAuthMessage);

			OAuthAccessor oAuthAccessor = getOAuthAccessor(
				oAuthMessage, oAuthUser);

			_oAuth.validateOAuthMessage(oAuthMessage, oAuthAccessor);

			authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
			authVerifierResult.setUserId(oAuthUser.getUserId());

			int accessLevel = getAccessLevel(oAuthUser);

			if (accessLevel == OAuthApplicationConstants.ACCESS_READ) {
				ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName(
					String.valueOf(
						OAuthSAPEntryActivator.SAP_ENTRY_OBJECT_ARRAYS[0][0]));
			}
			else if (accessLevel == OAuthApplicationConstants.ACCESS_WRITE) {
				ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName(
					String.valueOf(
						OAuthSAPEntryActivator.SAP_ENTRY_OBJECT_ARRAYS[1][0]));
			}
		}
		catch (Exception exception) {
			try {
				boolean sendBody = GetterUtil.getBoolean(
					properties.getProperty("send.body"));

				_oAuth.handleException(
					httpServletRequest, accessControlContext.getResponse(),
					exception, sendBody);

				authVerifierResult.setState(
					AuthVerifierResult.State.INVALID_CREDENTIALS);
			}
			catch (OAuthException oAuthException) {
				throw new AuthException(oAuthException);
			}
		}

		return authVerifierResult;
	}

	protected int getAccessLevel(OAuthUser oAuthUser) throws PortalException {
		OAuthApplication oAuthApplication =
			_oAuthApplicationLocalService.getOAuthApplication(
				oAuthUser.getOAuthApplicationId());

		return oAuthApplication.getAccessLevel();
	}

	protected OAuthAccessor getOAuthAccessor(
			OAuthMessage oAuthMessage, OAuthUser oAuthUser)
		throws PortalException {

		OAuthAccessor oAuthAccessor = new DefaultOAuthAccessor(
			_oAuth.getOAuthConsumer(oAuthMessage));

		oAuthAccessor.setAccessToken(oAuthUser.getAccessToken());
		oAuthAccessor.setRequestToken(null);
		oAuthAccessor.setTokenSecret(oAuthUser.getAccessSecret());

		return oAuthAccessor;
	}

	protected OAuthUser getOAuthUser(OAuthMessage oAuthMessage)
		throws IOException, OAuthException {

		if ((oAuthMessage == null) ||
			Validator.isNull(oAuthMessage.getToken())) {

			net.oauth.OAuthException oAuthException = new OAuthProblemException(
				net.oauth.OAuth.Problems.PARAMETER_ABSENT);

			throw new OAuthException(oAuthException);
		}

		OAuthUser oAuthUser = _oAuthUserLocalService.fetchOAuthUser(
			oAuthMessage.getToken());

		if (oAuthUser == null) {
			net.oauth.OAuthException oAuthException = new OAuthProblemException(
				net.oauth.OAuth.Problems.TOKEN_REJECTED);

			throw new OAuthException(oAuthException);
		}

		return oAuthUser;
	}

	protected boolean isUsingOAuth(HttpServletRequest httpServletRequest) {
		String oAuthToken = ParamUtil.getString(
			httpServletRequest, net.oauth.OAuth.OAUTH_TOKEN);

		if (Validator.isNotNull(oAuthToken)) {
			return true;
		}

		String authorization = GetterUtil.getString(
			httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));

		if (Validator.isNotNull(authorization)) {
			String authScheme = authorization.substring(0, 5);

			if (StringUtil.equalsIgnoreCase(authScheme, _OAUTH)) {
				return true;
			}
		}

		return false;
	}

	private static final String _OAUTH = "OAuth";

	@Reference
	private OAuth _oAuth;

	@Reference(unbind = "-")
	private OAuthApplicationLocalService _oAuthApplicationLocalService;

	@Reference(unbind = "-")
	private OAuthUserLocalService _oAuthUserLocalService;

}