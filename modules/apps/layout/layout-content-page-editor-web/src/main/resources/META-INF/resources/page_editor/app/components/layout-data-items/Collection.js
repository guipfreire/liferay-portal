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

import ClayLayout from '@clayui/layout';
import React, {useEffect, useState} from 'react';

import CollectionService from '../../services/CollectionService';
import {useDispatch, useSelector} from '../../store/index';
import {CollectionItemContextProvider} from '../CollectionItemContext';
import UnsafeHTML from '../UnsafeHTML';

const COLLECTION_ID_DIVIDER = '$';

function collectionIsMapped(collectionConfig) {
	return collectionConfig.collection;
}

function getCollectionPrefix(collectionId, index) {
	return `collection-${collectionId}-${index}${COLLECTION_ID_DIVIDER}`;
}

export function getToControlsId(collectionId, index) {
	return (itemId) => {
		if (!itemId) {
			return null;
		}

		return `${getCollectionPrefix(collectionId, index)}${itemId}`;
	};
}

export function fromControlsId(controlsItemId) {
	if (!controlsItemId) {
		return null;
	}

	const [, itemId] = controlsItemId.split(COLLECTION_ID_DIVIDER);

	return itemId || controlsItemId;
}

const NotCollectionSelectedMessage = () => (
	<div className="page-editor__collection__not-collection-selected-message">
		{Liferay.Language.get('no-collection-selected-yet')}
	</div>
);

const Grid = ({
	child,
	collection,
	collectionFields,
	collectionId,
	collectionLength,
	numberOfColumns,
	numberOfItems,
}) => {
	const maxNumberOfItems = Math.min(collectionLength, numberOfItems);
	const numberOfRows = Math.ceil(maxNumberOfItems / numberOfColumns);

	const createRows = () => {
		const rows = [];

		for (let i = 0; i < numberOfRows; i++) {
			const columns = [];

			for (let j = 0; j < numberOfColumns; j++) {
				const index = [i, j].join('-');
				const itemCount = i * numberOfColumns + j;

				columns.push(
					<ClayLayout.Col key={index} size={12 / numberOfColumns}>
						{itemCount < maxNumberOfItems && (
							<CollectionItemContextProvider
								key={index}
								value={{
									collectionFields,
									collectionItem:
										collection[i * numberOfColumns + j],
									collectionItemIndex:
										i * numberOfColumns + j,
									fromControlsId:
										itemCount === 0 ? null : fromControlsId,
									toControlsId:
										itemCount === 0
											? null
											: getToControlsId(
													collectionId,
													index
											  ),
								}}
							>
								{React.cloneElement(child)}
							</CollectionItemContextProvider>
						)}
					</ClayLayout.Col>
				);
			}

			rows.push(<ClayLayout.Row key={i}>{columns}</ClayLayout.Row>);
		}

		return rows;
	};

	return createRows();
};

const DEFAULT_COLLECTION = {
	items: [{defaultTitle: Liferay.Language.get('title')}],
	length: 1,
};

const Collection = React.forwardRef(({children, item}, ref) => {
	const child = React.Children.toArray(children)[0];
	const collectionConfig = item.config;

	const dispatch = useDispatch();

	const segmentsExperienceId = useSelector(
		(state) => state.segmentsExperienceId
	);

	const [collection, setCollection] = useState(DEFAULT_COLLECTION);

	useEffect(() => {
		if (collectionConfig.collection) {
			CollectionService.getCollectionField({
				collection: collectionConfig.collection,
				listItemStyle: collectionConfig.listItemStyle || null,
				listStyle: collectionConfig.listStyle,
				onNetworkStatus: dispatch,
				segmentsExperienceId,
				size: collectionConfig.numberOfItems,
				templateKey: collectionConfig.templateKey || null,
			})
				.then((response) => {
					setCollection(
						response.length > 0 ? response : DEFAULT_COLLECTION
					);
				})
				.catch((error) => {
					if (process.env.NODE_ENV === 'development') {
						console.error(error);
					}
				});
		}
	}, [
		collectionConfig.collection,
		collectionConfig.listItemStyle,
		collectionConfig.listStyle,
		collectionConfig.numberOfItems,
		collectionConfig.templateKey,
		dispatch,
		segmentsExperienceId,
	]);

	const [collectionFields, setCollectionFields] = useState([]);

	useEffect(() => {
		if (collectionConfig.collection) {
			CollectionService.getCollectionMappingFields({
				itemSubtype: collectionConfig.collection.itemSubtype || '',
				itemType: collectionConfig.collection.itemType,
				onNetworkStatus: dispatch,
			})
				.then((response) => {
					setCollectionFields(response);
				})
				.catch((error) => {
					if (process.env.NODE_ENV === 'development') {
						console.error(error);
					}
				});
		}
	}, [dispatch, collectionConfig.collection]);

	return (
		<div className="page-editor__collection" ref={ref}>
			{!collectionIsMapped(collectionConfig) ? (
				<NotCollectionSelectedMessage />
			) : collection.content ? (
				<UnsafeHTML markup={collection.content} />
			) : (
				<Grid
					child={child}
					collection={collection.items}
					collectionFields={collectionFields}
					collectionId={item.itemId}
					collectionLength={collection.items.length}
					numberOfColumns={collectionConfig.numberOfColumns}
					numberOfItems={collectionConfig.numberOfItems}
				/>
			)}
		</div>
	);
});

export default Collection;
