package com.feed_the_beast.mods.money.gui;

import com.feed_the_beast.ftblib.lib.gui.Panel;
import com.feed_the_beast.ftblib.lib.gui.Theme;
import com.feed_the_beast.ftblib.lib.gui.Widget;
import com.feed_the_beast.ftblib.lib.gui.WidgetLayout;
import com.feed_the_beast.mods.money.shop.ShopEntry;

/**
 * @author LatvianModder
 */
public class PanelShopEntryButtons extends Panel
{
	private final GuiShop guiShop;

	public PanelShopEntryButtons(GuiShop g)
	{
		super(g);
		guiShop = g;
	}

	@Override
	public void add(Widget widget)
	{
		if (guiShop.searchBox.getText().isEmpty() || widget.getTitle().toLowerCase().contains(guiShop.searchBox.getText().toLowerCase()))
		{
			super.add(widget);
		}
	}

	@Override
	public void addWidgets()
	{
		for (ShopEntry entry : guiShop.selectedTab.entries)
		{
			add(new ButtonShopEntry(this, entry));
		}
	}

	@Override
	public void alignWidgets()
	{
		setY(23);
		int prevWidth = width;

		if (widgets.isEmpty())
		{
			setWidth(100);
		}
		else
		{
			setWidth(0);

			for (Widget w : widgets)
			{
				setWidth(Math.max(width, w.width));
			}
		}

		setWidth(Math.max(width, prevWidth));

		for (Widget w : widgets)
		{
			w.setX(1);
			w.setWidth(width - 2);
		}

		setHeight(140);

		guiShop.scrollBar.setPosAndSize(posX + width + 6, posY - 1, 16, height + 2);
		guiShop.scrollBar.setMaxValue(align(new WidgetLayout.Vertical(1, 1, 1)));

		guiShop.setWidth(guiShop.scrollBar.posX + guiShop.scrollBar.width + 8);
		guiShop.setHeight(height + 32);

		guiShop.searchBox.setPosAndSize(8, 6, guiShop.width - 16, 12);
	}

	@Override
	public void drawBackground(Theme theme, int x, int y, int w, int h)
	{
		theme.drawPanelBackground(x, y, w, h);
	}
}