package interfaces;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import facultyAdminssion.Student;
import facultyAdminssion.ValidateStudent;

import org.eclipse.swt.widgets.Composite;

import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

public class Main {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(441, 249);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Button btnInregistrareStudent = new Button(shell, SWT.NONE);
		btnInregistrareStudent.setBounds(127, 45, 111, 25);
		btnInregistrareStudent.setText("Inregistrare student");
		
		Button btnAdmin = new Button(shell, SWT.NONE);
		btnAdmin.setBounds(146, 118, 75, 25);
		btnAdmin.setText("Admin");
		btnAdmin.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputAdmin window = new InputAdmin();
				window.open();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("default selection");
				
			}
	});
		
		btnInregistrareStudent.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputStudent window = new InputStudent();
				window.open();;
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("default selection");
				
			}
	});

	}

}
