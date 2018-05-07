package interfaces;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import facultyAdminssion.DatabaseOperations;
import facultyAdminssion.Student;
import facultyAdminssion.ValidateStudent;

import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class InputAdmin {

	protected Shell shell;
	
	  static Composite pageComposite;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InputAdmin window = new InputAdmin();
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
		shell.setSize(968, 350);
		shell.setText("SWT Application");
		
		
		Button btnCalculeazaRepartizare = new Button(shell, SWT.NONE);
		btnCalculeazaRepartizare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Label lBudget = new Label(shell, SWT.NONE);
				lBudget.setBounds(250, 100, 160, 15);
				lBudget.setText("Numar locuri buget: ");
				
				Text txtBudget = new Text(shell, SWT.BORDER);
				txtBudget.setBounds(500, 100, 160, 21);
				
				Label lTax = new Label(shell, SWT.NONE);
				lTax.setBounds(250, 130, 150, 15);
				lTax.setText("Numar locuri taxa");
				
				Text txtTax = new Text(shell, SWT.BORDER);
				txtTax.setBounds(500, 130, 150, 21);
				
				Button btnCompute = new Button(shell, SWT.NONE);
				btnCompute.setBounds(500, 200, 150, 21);
				btnCompute.setText("Calculeaza");
				
				btnCompute.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						boolean ok = DatabaseOperations.compute(txtBudget.getText(), txtTax.getText());
						String msg = "";
						int style;
						if(ok==false) {
							style = SWT.ICON_ERROR;
							msg="Nu ati completat toate campurile";
						}
						else {
							style = SWT.OK;
							msg = "Repartitia a fost realizata";
						}
						MessageBox messageBox = new MessageBox(shell, style);
						messageBox.setMessage(msg);
						int rc = messageBox.open();
					}
				});
				
				
			}
		});
		btnCalculeazaRepartizare.setBounds(10, 10, 168, 25);
		btnCalculeazaRepartizare.setText("Calculeaza repartizare");
		
		Button btnSelecteazaStudenti = new Button(shell, SWT.NONE);
		btnSelecteazaStudenti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<List<String>> students = DatabaseOperations.selectStudents();
				createTable(students );		
				
			}
		});
		btnSelecteazaStudenti.setBounds(10, 57, 121, 25);
		btnSelecteazaStudenti.setText("Selecteaza studenti");
		
		Button btnActualizeazaStudent = new Button(shell, SWT.NONE);
		btnActualizeazaStudent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (Control kid : shell.getChildren()) {
		        	if(!kid.getClass().getName().equals("org.eclipse.swt.widgets.Button"))
		        		kid.dispose();
		          }
				
				Label lCnp = new Label(shell, SWT.NONE);
				lCnp.setBounds(250, 100, 160, 15);
				lCnp.setText("CNP student pentru actualizat");
				
				Text txtCnp = new Text(shell, SWT.BORDER);
				txtCnp.setBounds(500, 100, 160, 21);
				
				Label lCact = new Label(shell, SWT.NONE);
				lCact.setBounds(250, 130, 150, 15);
				lCact.setText("Coloana pentru actualizare");
				
				Text txtCact = new Text(shell, SWT.BORDER);
				txtCact.setBounds(500, 130, 150, 21);
				
				Label lValAct = new Label(shell, SWT.NONE);
				lValAct.setBounds(250, 160, 150, 15);
				lValAct.setText("Valoare noua: ");
				
				Text txtValAct = new Text(shell, SWT.BORDER);
				txtValAct.setBounds(500, 160, 150, 21);
				
				Button btnActualizeaza = new Button(shell, SWT.NONE);
				btnActualizeaza.setBounds(500, 200, 150, 21);
				btnActualizeaza.setText("Actualizeaza student");
				
				btnActualizeaza.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						DatabaseOperations.updateStudent(txtCnp.getText(), txtCact.getText(), txtValAct.getText());
						
					}
				});
				
			}
		});
		btnActualizeazaStudent.setBounds(10, 113, 168, 25);
		btnActualizeazaStudent.setText("Actualizeaza student");
		
		Button btnVizualizeazaStudentiTaxa = new Button(shell, SWT.NONE);
		btnVizualizeazaStudentiTaxa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<List<String>> students = DatabaseOperations.selectStudentsBT(0);
				createTableBT(students );		
			}
		});
		btnVizualizeazaStudentiTaxa.setBounds(10, 165, 144, 25);
		btnVizualizeazaStudentiTaxa.setText("Vizualizeaza studenti taxa");
		
		Button btnVizualizeazaStudentiBuget = new Button(shell, SWT.NONE);
		btnVizualizeazaStudentiBuget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<List<String>> students = DatabaseOperations.selectStudentsBT(1);
				createTableBT(students );		
				
			}
		});
		btnVizualizeazaStudentiBuget.setBounds(10, 211, 168, 25);
		btnVizualizeazaStudentiBuget.setText("Vizualizeaza studenti buget");
		
		Button btnStergeStudent = new Button(shell, SWT.NONE);
		btnStergeStudent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Label lCnp = new Label(shell, SWT.NONE);
				lCnp.setBounds(250, 100, 160, 15);
				lCnp.setText("CNP student pentru actualizat");
				
				Text txtCnp = new Text(shell, SWT.BORDER);
				txtCnp.setBounds(500, 100, 160, 21);

				Button btnActualizeaza = new Button(shell, SWT.NONE);
				btnActualizeaza.setBounds(500, 200, 150, 21);
				btnActualizeaza.setText("Sterge student");
				
				btnActualizeaza.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						boolean ok = DatabaseOperations.deleteStudent(txtCnp.getText());
						String msg = "";
						int style;
						if(ok==false) {
							style = SWT.ICON_ERROR;
							msg="Studentul nu a putut fi sters";
						}
						else {
							style = SWT.OK;
							msg = "Studentul a fost sters";
						}
						MessageBox messageBox = new MessageBox(shell, style);
						messageBox.setMessage(msg);
						int rc = messageBox.open();

						
					}
				});
				
			}
		});
		btnStergeStudent.setBounds(10, 256, 144, 25);
		btnStergeStudent.setText("Sterge student");
		
		
	}
	
	public  void createTableBT(ArrayList<List<String>> students ) {
		for (Control kid : shell.getChildren()) {
        	if(!kid.getClass().getName().equals("org.eclipse.swt.widgets.Button"))
        		kid.dispose();
          }
			Table table = new Table(shell, SWT.MULTI | SWT.BORDER
		        | SWT.FULL_SELECTION);
		    table.setLinesVisible(true);
		    table.setHeaderVisible(true);
		    String[] titles = { "Nume", "Medie" };
		    for (int i = 0; i < titles.length; i++) {
		      TableColumn column = new TableColumn(table, SWT.NONE);
		      column.setText(titles[i]);
		    }
		    for (List<String> s: students) {
			      TableItem item = new TableItem(table, SWT.NONE);

		    	for(int i=0; i<s.size(); i++) {
			      item.setText(i, s.get(i));
			     
			     }
		    }
		    for (int i = 0; i < titles.length; i++) {
		      table.getColumn(i).pack();
		    }
		    table.setSize(table.computeSize(SWT.DEFAULT, 200));
		    table.setBounds(200, 10, 150, 200);
	} 
	
	public  void createTable(ArrayList<List<String>> students ) {
		for (Control kid : shell.getChildren()) {
        	if(!kid.getClass().getName().equals("org.eclipse.swt.widgets.Button"))
        		kid.dispose();
          }
			Table table = new Table(shell, SWT.MULTI | SWT.BORDER
		        | SWT.FULL_SELECTION);
		    table.setLinesVisible(true);
		    table.setHeaderVisible(true);
		    String[] titles = { "Nume", "CNP", "Adresa", "Email", "Telefon",
		        "Nota BAC", "Nota info", "Nota mate", "Nota examen" };
		    for (int i = 0; i < titles.length; i++) {
		      TableColumn column = new TableColumn(table, SWT.NONE);
		      column.setText(titles[i]);
		    }
		    for (List<String> s: students) {
			      TableItem item = new TableItem(table, SWT.NONE);

		    	for(int i=0; i<s.size(); i++) {
			      item.setText(i, s.get(i));
			     
			     }
		    }
		    for (int i = 0; i < titles.length; i++) {
		      table.getColumn(i).pack();
		    }
		    table.setSize(table.computeSize(SWT.DEFAULT, 200));
		    table.setBounds(200, 10, 700, 300);
	} 
}

