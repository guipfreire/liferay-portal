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

package com.liferay.exportimport.content.processor;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.osgi.util.StringPlus;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Gergely Mathe
 * @author Máté Thurzó
 */
public class ExportImportContentProcessorRegistryUtil {

	public static ExportImportContentProcessor<String>
		getExportImportContentProcessor(String className) {

		return _exportImportContentProcessorRegistryUtil.
			_getExportImportContentProcessor(className);
	}

	public static List<ExportImportContentProcessor<String>>
		getExportImportContentProcessors() {

		return _exportImportContentProcessorRegistryUtil.
			_getExportImportContentProcessors();
	}

	private ExportImportContentProcessorRegistryUtil() {
		Bundle bundle = FrameworkUtil.getBundle(
			ExportImportContentProcessorRegistryUtil.class);

		_bundleContext = bundle.getBundleContext();

		_serviceTracker = ServiceTrackerFactory.open(
			_bundleContext,
			(Class<ExportImportContentProcessor<String>>)
				(Class<?>)ExportImportContentProcessor.class,
			new ExportImportContentProcessorServiceTrackerCustomizer());
	}

	private ExportImportContentProcessor<String>
		_getExportImportContentProcessor(String className) {

		return _exportImportContentProcessors.get(className);
	}

	private List<ExportImportContentProcessor<String>>
		_getExportImportContentProcessors() {

		Collection<ExportImportContentProcessor<String>> values =
			_exportImportContentProcessors.values();

		return ListUtil.fromCollection(values);
	}

	private static final ExportImportContentProcessorRegistryUtil
		_exportImportContentProcessorRegistryUtil =
			new ExportImportContentProcessorRegistryUtil();

	private final BundleContext _bundleContext;
	private final Map<String, ExportImportContentProcessor<String>>
		_exportImportContentProcessors = new ConcurrentHashMap<>();
	private final ServiceTracker
		<ExportImportContentProcessor<String>,
		 ExportImportContentProcessor<String>> _serviceTracker;

	private class ExportImportContentProcessorServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<ExportImportContentProcessor<String>,
			 ExportImportContentProcessor<String>> {

		@Override
		public ExportImportContentProcessor<String> addingService(
			ServiceReference<ExportImportContentProcessor<String>>
				serviceReference) {

			ExportImportContentProcessor<String> exportImportContentProcessor =
				_bundleContext.getService(serviceReference);

			List<String> modelClassNames = StringPlus.asList(
				serviceReference.getProperty("model.class.name"));

			for (String modelClassName : modelClassNames) {
				_exportImportContentProcessors.put(
					modelClassName, exportImportContentProcessor);
			}

			return exportImportContentProcessor;
		}

		@Override
		public void modifiedService(
			ServiceReference<ExportImportContentProcessor<String>>
				serviceReference,
			ExportImportContentProcessor<String> exportImportContentProcessor) {

			removedService(serviceReference, exportImportContentProcessor);

			addingService(serviceReference);
		}

		@Override
		public void removedService(
			ServiceReference<ExportImportContentProcessor<String>>
				serviceReference,
			ExportImportContentProcessor<String> exportImportContentProcessor) {

			_bundleContext.ungetService(serviceReference);

			List<String> modelClassNames = StringPlus.asList(
				serviceReference.getProperty("model.class.name"));

			for (String modelClassName : modelClassNames) {
				_exportImportContentProcessors.remove(modelClassName);
			}
		}

	}

}