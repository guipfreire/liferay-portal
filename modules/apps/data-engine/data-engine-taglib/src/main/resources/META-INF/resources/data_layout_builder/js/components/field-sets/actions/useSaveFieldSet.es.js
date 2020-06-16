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

import {useContext} from 'react';

import AppContext from '../../../AppContext.es';
import {UPDATE_DATA_DEFINITION, UPDATE_FIELDSETS} from '../../../actions.es';
import DataLayoutBuilderContext from '../../../data-layout-builder/DataLayoutBuilderContext.es';
import {updateItem} from '../../../utils/client.es';
import {
	containsField,
	normalizeDataLayoutRows,
} from '../../../utils/dataLayoutVisitor.es';
import saveDataDefinition from '../../../utils/saveDataDefinition.es';
import {errorToast, successToast} from '../../../utils/toast.es';

export default ({
	childrenContext,
	defaultLanguageId,
	fieldSet,
	otherProps: {DataLayout},
}) => {
	const [context, dispatch] = useContext(AppContext);
	const {dataDefinition, dataLayout, fieldSets} = context;
	const {state: childrenState} = childrenContext;
	const [dataLayoutBuilder] = useContext(DataLayoutBuilderContext);

	return (fieldSetName) => {
		const {id} = fieldSet;
		const {
			dataDefinition: {dataDefinitionFields},
			dataLayout: {dataLayoutPages},
		} = childrenState;

		const name = {
			...fieldSet.name,
			[defaultLanguageId]: fieldSetName,
		};

		fieldSet.dataDefinitionFields = dataDefinitionFields;
		fieldSet.defaultDataLayout.dataLayoutPages = dataLayoutPages;
		fieldSet.name = name;
		let fieldName;

		const dataDefinitionField = dataDefinition.dataDefinitionFields.find(
			({customProperties: {ddmStructureId}}) => ddmStructureId == id
		);

		const getDataDefinitionFields = () => {
			const fields = dataDefinition.dataDefinitionFields;

			return fields.map((ddField) => {
				if (ddField.name === fieldName) {
					return {
						...ddField,
						label: name,
						nestedDataDefinitionFields: dataDefinitionFields,
					};
				}

				return ddField;
			});
		};

		updateItem(`/o/data-engine/v2.0/data-definitions/${id}`, fieldSet)
			.then(() => {
				if (dataDefinitionField) {
					fieldName = dataDefinitionField.name;
					if (containsField(dataLayout.dataLayoutPages, fieldName)) {
						dataLayoutBuilder.dispatch('fieldEdited', {
							fieldName,
							propertyName: 'nestedFields',
							propertyValue: dataDefinitionFields.map(({name}) =>
								DataLayout.getDDMFormField(
									childrenState.dataDefinition,
									name
								)
							),
						});

						dataLayoutBuilder.dispatch('fieldEdited', {
							fieldName,
							propertyName: 'rows',
							propertyValue: normalizeDataLayoutRows(
								dataLayoutPages
							),
						});

						dataLayoutBuilder.dispatch('fieldEdited', {
							fieldName,
							propertyName: 'label',
							propertyValue: fieldSetName,
						});
					}
					else {
						dispatch({
							payload: {
								dataDefinition: {
									...dataDefinition,
									dataDefinitionFields: getDataDefinitionFields(),
								},
							},
							type: UPDATE_DATA_DEFINITION,
						});
					}

					return saveDataDefinition({
						...context,
						dataDefinition: {
							...dataDefinition,
							dataDefinitionFields: getDataDefinitionFields(),
						},
					});
				}

				return Promise.resolve();
			})
			.then(() => {
				dispatch({
					payload: {
						fieldSets: fieldSets.map((field) => {
							if (field.id === id) {
								return fieldSet;
							}

							return field;
						}),
					},
					type: UPDATE_FIELDSETS,
				});

				return Promise.resolve();
			})
			.then(() => {
				successToast(Liferay.Language.get('fieldset-saved'));
			})
			.catch(({message}) => errorToast(message));
	};
};
