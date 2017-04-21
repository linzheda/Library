package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class BookImage extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookImage(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(null);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(BookImage.class, "/images/2008121612011586_2.jpg"));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
