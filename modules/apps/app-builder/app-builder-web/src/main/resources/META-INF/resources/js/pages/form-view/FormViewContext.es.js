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

import {createContext} from 'react';
import {PagesVisitor} from 'dynamic-data-mapping-form-renderer/js/util/visitors.es';
import {containsField} from '../../utils/dataLayoutVisitor.es';
import {
	ADD_CUSTOM_OBJECT_FIELD,
	EDIT_CUSTOM_OBJECT_FIELD,
	UPDATE_DATA_DEFINITION,
	UPDATE_DATA_LAYOUT,
	UPDATE_DATA_LAYOUT_NAME,
	UPDATE_FIELD_TYPES,
	UPDATE_IDS,
	UPDATE_PAGES,
	UPDATE_FOCUSED_FIELD
} from './actions.es';
import generateDataDefinitionFieldName from '../../utils/generateDataDefinitionFieldName.es';

const FormViewContext = createContext();

const initialState = {
	dataDefinition: {
		dataDefinitionFields: [],
		name: {}
	},
	dataDefinitionId: 0,
	dataLayout: {
		dataLayoutPages: [],
		name: {}
	},
	dataLayoutId: 0,
	fieldTypes: [],
	focusedCustomObjectField: {},
	focusedField: {}
};

const addCustomObjectField = ({
	dataDefinition,
	dataLayoutBuilder,
	fieldTypes,
	fieldTypeName
}) => {
	const fieldType = fieldTypes.find(({name}) => name === fieldTypeName);
	const dataDefinitionField = dataLayoutBuilder.getDefinitionField(fieldType);

	return {
		...dataDefinitionField,
		label: {
			[themeDisplay.getLanguageId()]: fieldType.label
		},
		name: generateDataDefinitionFieldName(dataDefinition, fieldType.label)
	};
};

const editFocusedCustomObjectField = ({
	focusedCustomObjectField,
	propertyName,
	propertyValue
}) => {
	let localizableProperty = false;
	const {settingsContext} = focusedCustomObjectField;
	const visitor = new PagesVisitor(settingsContext.pages);
	const newSettingsContext = {
		...settingsContext,
		pages: visitor.mapFields(field => {
			const {fieldName, localizable} = field;

			if (fieldName === propertyName) {
				localizableProperty = localizable;

				return {
					...field,
					localizedValue: {
						...field.localizedValue,
						[themeDisplay.getLanguageId()]: propertyValue
					},
					value: propertyValue
				};
			}

			return field;
		})
	};

	if (localizableProperty) {
		propertyValue = {
			[themeDisplay.getLanguageId()]: propertyValue
		};
	}

	return {
		...focusedCustomObjectField,
		[propertyName]: propertyValue,
		settingsContext: newSettingsContext
	};
};

const setDataDefinitionFields = (
	dataLayoutBuilder,
	dataDefinition,
	dataLayout
) => {
	const {dataDefinitionFields} = dataDefinition;
	const {dataLayoutPages} = dataLayout;

	const {pages} = dataLayoutBuilder.getStore();
	const visitor = new PagesVisitor(pages);

	const newFields = [];

	visitor.mapFields(field => {
		const definitionField = dataLayoutBuilder.getDefinitionField(field);

		newFields.push(definitionField);
	});

	return newFields.concat(
		dataDefinitionFields.filter(
			field =>
				!containsField(dataLayoutPages, field.name) &&
				!newFields.some(({name}) => name === field.name)
		)
	);
};

const setDataLayout = dataLayoutBuilder => {
	const {pages} = dataLayoutBuilder.getStore();
	const {layout} = dataLayoutBuilder.getDefinitionAndLayout(pages);

	return layout;
};

const createReducer = dataLayoutBuilder => {
	return (state = initialState, action) => {
		switch (action.type) {
			case ADD_CUSTOM_OBJECT_FIELD: {
				const {fieldTypeName} = action.payload;
				const {dataDefinition, fieldTypes} = state;
				const newCustomObjectField = addCustomObjectField({
					dataDefinition,
					dataLayoutBuilder,
					fieldTypeName,
					fieldTypes
				});

				return {
					...state,
					dataDefinition: {
						...dataDefinition,
						dataDefinitionFields: [
							...dataDefinition.dataDefinitionFields,
							newCustomObjectField
						]
					},
					focusedCustomObjectField: {
						...newCustomObjectField,
						settingsContext: dataLayoutBuilder.getFieldSettingsContext(
							newCustomObjectField
						)
					}
				};
			}
			case EDIT_CUSTOM_OBJECT_FIELD: {
				const {dataDefinition, focusedCustomObjectField} = state;
				const editedFocusedCustomObjectField = editFocusedCustomObjectField(
					{
						...action.payload,
						focusedCustomObjectField
					}
				);
				const {
					settingsContext,
					...editedCustomObjectField
				} = editedFocusedCustomObjectField;

				return {
					...state,
					dataDefinition: {
						...dataDefinition,
						dataDefinitionFields: dataDefinition.dataDefinitionFields.map(
							dataDefinitionField => {
								if (
									dataDefinitionField.name ===
									focusedCustomObjectField.name
								) {
									return editedCustomObjectField;
								}

								return dataDefinitionField;
							}
						)
					},
					focusedCustomObjectField: {
						...editedFocusedCustomObjectField,
						settingsContext
					}
				};
			}
			case UPDATE_DATA_DEFINITION: {
				const {dataDefinition} = action.payload;

				return {
					...state,
					dataDefinition: {
						...state.dataDefinition,
						...dataDefinition
					}
				};
			}
			case UPDATE_DATA_LAYOUT: {
				const {dataLayout} = action.payload;

				return {
					...state,
					dataLayout: {
						...state.dataLayout,
						...dataLayout
					}
				};
			}
			case UPDATE_DATA_LAYOUT_NAME: {
				const {name} = action.payload;

				return {
					...state,
					dataLayout: {
						...state.dataLayout,
						name
					}
				};
			}
			case UPDATE_FIELD_TYPES: {
				const {fieldTypes} = action.payload;

				return {
					...state,
					fieldTypes: fieldTypes.filter(({system}) => !system)
				};
			}
			case UPDATE_FOCUSED_FIELD: {
				const {focusedField} = action.payload;

				return {
					...state,
					focusedField
				};
			}
			case UPDATE_IDS: {
				const {dataDefinitionId, dataLayoutId} = action.payload;

				return {
					...state,
					dataDefinitionId,
					dataLayoutId
				};
			}
			case UPDATE_PAGES: {
				const {dataDefinition, dataLayout} = state;

				return {
					...state,
					dataDefinition: {
						...dataDefinition,
						dataDefinitionFields: setDataDefinitionFields(
							dataLayoutBuilder,
							dataDefinition,
							dataLayout
						)
					},
					dataLayout: {
						...dataLayout,
						...setDataLayout(dataLayoutBuilder)
					}
				};
			}
			default:
				return state;
		}
	};
};

export default FormViewContext;

export {initialState, createReducer};
