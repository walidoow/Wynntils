/*
 * Copyright © Wynntils 2023-2025.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.features.utilities;

import com.wynntils.core.components.Managers;
import com.wynntils.core.components.Models;
import com.wynntils.core.consumers.features.Feature;
import com.wynntils.core.persisted.config.Category;
import com.wynntils.core.persisted.config.ConfigCategory;
import com.wynntils.mc.event.ScreenOpenedEvent;
import com.wynntils.models.containers.containers.CharacterInfoContainer;
import com.wynntils.screens.base.widgets.WynntilsButton;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;

import java.net.URI;

@ConfigCategory(Category.UTILITIES)
public class WynnbuilderExportFeature extends Feature {
    @SubscribeEvent
    public void onCharacterInfoScreenOpened(ScreenOpenedEvent.Post e) {
        if (!(e.getScreen() instanceof ContainerScreen screen)) return;
        if (!(Models.Container.getCurrentContainer() instanceof CharacterInfoContainer)) return;

        screen.addRenderableWidget(
                new ExternalLinkButton(screen.width / 2 + 4 * ExternalLinkButton.BUTTON_WIDTH, screen.topPos - 24));
    }

    private static final class ExternalLinkButton extends WynntilsButton {
        private static final int BUTTON_WIDTH = 20;
        private static final int BUTTON_HEIGHT = 20;

        private ExternalLinkButton(int x, int y) {
            super(
                    x,
                    y,
                    BUTTON_WIDTH,
                    BUTTON_HEIGHT,
                    Component.literal("Wynnbuilder"));
        }

        @Override
        public void onPress() {
            Managers.Net.openLink(URI.create("https://wynnbuilder.github.io/"));
        }
    }
}
