package com.zd.book.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class ReportForms extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ReportForms(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(ReportForms.class, "/images/框框.jpg"));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		lblNewLabel.setBackgroundImage(null);
		lblNewLabel.setBounds(472, 64, 78, 27);
		lblNewLabel.setText("借阅报表");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
