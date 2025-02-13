<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<script type="text/javascript">
	window.$crisp = [];

	window.CRISP_WEBSITE_ID = '<%= clickToChatProviderAccountId %>';

	(function () {
		d = document;

		s = d.createElement('script');

		s.src = 'https://client.crisp.chat/l.js';

		s.async = 1;

		d.getElementsByTagName('head')[0].appendChild(s);
	})();

	<c:if test="<%= themeDisplay.isSignedIn() %>">
		$crisp.push(['set', 'user:email', '<%= user.getScreenName() %>']);
		$crisp.push(['set', 'user:nickname', '<%= user.getEmailAddress() %>']);
	</c:if>
</script>