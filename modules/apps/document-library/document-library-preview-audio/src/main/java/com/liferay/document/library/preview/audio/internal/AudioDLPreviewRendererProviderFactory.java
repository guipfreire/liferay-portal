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

package com.liferay.document.library.preview.audio.internal;

import com.liferay.document.library.kernel.model.DLProcessorConstants;
import com.liferay.document.library.kernel.util.AudioProcessor;
import com.liferay.document.library.kernel.util.DLProcessor;
import com.liferay.document.library.preview.DLPreviewRendererProvider;
import com.liferay.document.library.service.DLFileVersionPreviewLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.Set;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = {})
public class AudioDLPreviewRendererProviderFactory {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_dlProcessorServiceTracker =
			new ServiceTracker<DLProcessor, ServiceRegistration<?>>(
				bundleContext, DLProcessor.class, null) {

				@Override
				public ServiceRegistration<?> addingService(
					ServiceReference<DLProcessor> serviceReference) {

					DLProcessor dlProcessor = bundleContext.getService(
						serviceReference);

					if (!DLProcessorConstants.AUDIO_PROCESSOR.equals(
							dlProcessor.getType())) {

						bundleContext.ungetService(serviceReference);

						return null;
					}

					AudioProcessor audioProcessor = (AudioProcessor)dlProcessor;

					Set<String> audioMimeTypes =
						audioProcessor.getAudioMimeTypes();

					Dictionary<String, Object> properties =
						new HashMapDictionary<>();

					properties.put("content.type", audioMimeTypes.toArray());

					Object serviceRanking = serviceReference.getProperty(
						Constants.SERVICE_RANKING);

					if (serviceRanking != null) {
						properties.put(
							Constants.SERVICE_RANKING, serviceRanking);
					}

					return bundleContext.registerService(
						DLPreviewRendererProvider.class,
						new AudioDLPreviewRendererProvider(
							_dlFileVersionPreviewLocalService, _dlURLHelper,
							_servletContext),
						properties);
				}

				@Override
				public void removedService(
					ServiceReference<DLProcessor> serviceReference,
					ServiceRegistration<?> serviceRegistration) {

					serviceRegistration.unregister();

					bundleContext.ungetService(serviceReference);
				}

			};

		_dlProcessorServiceTracker.open();
	}

	@Deactivate
	protected void deactivate() {
		_dlProcessorServiceTracker.close();
	}

	@Reference
	private DLFileVersionPreviewLocalService _dlFileVersionPreviewLocalService;

	private ServiceTracker<DLProcessor, ServiceRegistration<?>>
		_dlProcessorServiceTracker;

	@Reference
	private DLURLHelper _dlURLHelper;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.document.library.preview.audio)"
	)
	private ServletContext _servletContext;

}