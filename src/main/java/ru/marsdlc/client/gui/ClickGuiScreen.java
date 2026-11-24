
package ru.marsdlc.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import ru.marsdlc.client.theme.ThemeManager;
import ru.marsdlc.client.theme.ThemeManager.Theme;

public class ClickGuiScreen extends Screen {

    private static final int BG = 0xFF09090D;
    private static final int PANEL = 0xFF15151D;
    private static final int SIDEBAR = 0xFF101017;
    private static final int ROW = 0xFF20202A;

    private String selectedTab = "Render";

    public ClickGuiScreen() {
        super(Text.literal("marsDLC"));
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX,
                       int mouseY, float delta) {

        context.fill(0, 0, width, height, 0x99000000);

        int guiWidth = 430;
        int guiHeight = 270;

        int x = (width - guiWidth) / 2;
        int y = (height - guiHeight) / 2;

        context.fill(x, y, x + guiWidth, y + guiHeight, BG);

        // Sidebar
        context.fill(x, y, x + 115, y + guiHeight, SIDEBAR);

        // Header
        context.fill(x, y, x + guiWidth, y + 3,
                ThemeManager.getColor(0));

        context.drawTextWithShadow(
            textRenderer,
            Text.literal("marsDLC"),
            x + 14, y + 15,
            ThemeManager.getColor(0.2f)
        );

        context.drawTextWithShadow(
            textRenderer,
            Text.literal("1.0.0"),
            x + 14, y + 29,
            0xFF777783
        );

        // Tabs
        drawTab(context, x, y + 65, "Render", mouseX, mouseY);
        drawTab(context, x, y + 95, "Themes", mouseX, mouseY);

        // Content
        int contentX = x + 135;

        if (selectedTab.equals("Render")) {

            context.drawTextWithShadow(
                textRenderer,
                Text.literal("Render"),
                contentX, y + 25,
                0xFFFFFFFF
            );

            context.fill(
                contentX, y + 55,
                x + guiWidth - 20, y + 100,
                PANEL
            );

            context.fill(
                contentX, y + 55,
                contentX + 2, y + 100,
                ThemeManager.getColor(0.5f)
            );

            context.drawTextWithShadow(
                textRenderer,
                Text.literal("Interface"),
                contentX + 12, y + 65,
                0xFFFFFFFF
            );

            context.drawTextWithShadow(
                textRenderer,
                Text.literal("ClickGUI"),
                contentX + 12, y + 80,
                0xFF888894
            );

        } else {

            context.drawTextWithShadow(
                textRenderer,
                Text.literal("Themes"),
                contentX, y + 25,
                0xFFFFFFFF
            );

            Theme[] themes = Theme.values();

            for (int i = 0; i < themes.length; i++) {

                int rowY = y + 50 + i * 29;

                context.fill(
                    contentX, rowY,
                    x + guiWidth - 20, rowY + 24,
                    PANEL
                );

                context.fill(
                    contentX, rowY,
                    contentX + 3, rowY + 24,
                    ThemeManager.getColor(i * 0.5f)
                );

                String name = themes[i].name();

                context.drawTextWithShadow(
                    textRenderer,
                    Text.literal(name),
                    contentX + 12, rowY + 8,
                    ThemeManager.getTheme() == themes[i]
                        ? ThemeManager.getColor(i * 0.5f)
                        : 0xFFFFFFFF
                );

                if (ThemeManager.getTheme() == themes[i]) {
                    context.drawTextWithShadow(
                        textRenderer,
                        Text.literal("SELECTED"),
                        x + guiWidth - 85,
                        rowY + 8,
                        0xFFAAAAAA
                    );
                }
            }
        }

        super.render(context, mouseX, mouseY, delta);
    }

    private void drawTab(DrawContext context, int x, int y,
                         String name, int mouseX, int mouseY) {

        boolean selected = selectedTab.equals(name);

        if (selected) {
            context.fill(
                x + 7, y - 5,
                x + 108, y + 19,
                ROW
            );

            context.fill(
                x + 7, y - 5,
                x + 9, y + 19,
                ThemeManager.getColor(0.3f)
            );
        }

        context.drawTextWithShadow(
            textRenderer,
            Text.literal(name),
            x + 18, y + 3,
            selected ? 0xFFFFFFFF : 0xFF9999A5
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY,
                                int button) {

        if (button != 0) {
            return super.mouseClicked(mouseX, mouseY, button);
        }

        int guiWidth = 430;
        int guiHeight = 270;

        int x = (width - guiWidth) / 2;
        int y = (height - guiHeight) / 2;

        if (mouseX >= x && mouseX <= x + 115) {

            if (mouseY >= y + 60 && mouseY <= y + 90) {
                selectedTab = "Render";
                return true;
            }

            if (mouseY >= y + 90 && mouseY <= y + 120) {
                selectedTab = "Themes";
                return true;
            }
        }

        if (selectedTab.equals("Themes")) {

            Theme[] themes = Theme.values();

            for (int i = 0; i < themes.length; i++) {

                int rowY = y + 50 + i * 29;

                if (mouseX >= x + 135 &&
                    mouseX <= x + guiWidth - 20 &&
                    mouseY >= rowY &&
                    mouseY <= rowY + 24) {

                    ThemeManager.setTheme(themes[i]);
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
      }
