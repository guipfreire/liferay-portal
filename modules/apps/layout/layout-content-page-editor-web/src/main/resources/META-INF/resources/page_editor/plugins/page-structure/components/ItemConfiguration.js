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

import ClayTabs from '@clayui/tabs';
import React, {useEffect, useMemo, useState} from 'react';

import {
	useActiveItemId,
	useActiveItemType,
} from '../../../app/components/Controls';
import {useSelectorCallback} from '../../../app/store/index';
import {deepEqual} from '../../../app/utils/checkDeepEqual';
import {useId} from '../../../app/utils/useId';
import {PANELS, selectPanels} from '../selectors/selectPanels';
import PageStructureSidebarSection from './PageStructureSidebarSection';

export default function ItemConfiguration() {
	const activeItemId = useActiveItemId();
	const activeItemType = useActiveItemType();
	const [activePanelId, setActivePanelId] = useState(null);
	const tabIdPrefix = useId();
	const panelIdPrefix = useId();

	const {activeItem, panelsIds} = useSelectorCallback(
		(state) => selectPanels(activeItemId, activeItemType, state),
		[activeItemId, activeItemType],
		deepEqual
	);

	const panels = useMemo(
		() =>
			Object.entries(panelsIds)
				.filter(([, show]) => show)
				.map(([key]) => ({...PANELS[key], panelId: key})),
		[panelsIds]
	);

	useEffect(() => {
		setActivePanelId((panelId) => {
			if (panels.find((panel) => panel.panelId === panelId)) {
				return panelId;
			}

			return panels[0]?.panelId ?? '';
		});
	}, [panels]);

	if (!activeItem || !panels.length) {
		return null;
	}

	return (
		<PageStructureSidebarSection>
			<div className="page-editor__page-structure__item-configuration">
				<ClayTabs className="border-bottom" modern>
					{panels.map((panel) => (
						<ClayTabs.Item
							active={panel.panelId === activePanelId}
							innerProps={{
								'aria-controls': `${panelIdPrefix}-${panel.panelId}`,
								id: `${tabIdPrefix}-${panel.panelId}`,
							}}
							key={panel.panelId}
							onClick={() => setActivePanelId(panel.panelId)}
						>
							<span className="page-editor__page-structure__item-configuration-tab">
								{panel.label}
							</span>
						</ClayTabs.Item>
					))}
				</ClayTabs>

				<ClayTabs.Content
					activeIndex={panels.findIndex(
						(panel) => panel.panelId === activePanelId
					)}
				>
					{panels.map((panel) => {
						const Component = panel.component;

						return (
							<ClayTabs.TabPane
								aria-labelledby={`${tabIdPrefix}-${panel.panelId}`}
								className="p-3"
								id={`${panelIdPrefix}-${panel.panelId}`}
								key={panel.panelId}
							>
								<Component item={activeItem} />
							</ClayTabs.TabPane>
						);
					})}
				</ClayTabs.Content>
			</div>
		</PageStructureSidebarSection>
	);
}
