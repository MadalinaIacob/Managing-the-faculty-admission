package interfaces;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;

import facultyAdminssion.DatabaseOperations;
import facultyAdminssion.Student;
import facultyAdminssion.ValidateStudent;

import org.eclipse.swt.widgets.Button;

public class InputStudent {

	protected Shell shlInscriereFacultate;
	private Text txtStudentname;
	private Text txtMariagestudentname;
	private Text txtBirthDay;
	private Text txtBirthMonth;
	private Text txtBirthyear;
	private Text txtCnp;
	private Text txtBirthplace;
	private Text txtBirthcity;
	private Text txtBirthcountry;
	private Text txtNationality;
	private Text txtCitizenship;
	private Text txtPlace;
	private Text txtCity;
	private Text txtStreet;
	private Text txtNr;
	private Text txtBlock;
	private Text txtSc;
	private Text txtEt;
	private Text txtAp;
	private Text txtEmail;
	private Text txtPhone;
	private Text txtFathername;
	private Text txtMothername;
	private Text txtOldfaculty;
	private Text txtGradeexam;
	private Text txtGradeinfo;
	private Text txtGrademath;
	private boolean married, notMarried;
	private Text txtGradeadmexam;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InputStudent window = new InputStudent();
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
		shlInscriereFacultate.open();
		shlInscriereFacultate.layout();
		while (!shlInscriereFacultate.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlInscriereFacultate = new Shell();
		shlInscriereFacultate.setSize(756, 624);
		shlInscriereFacultate.setText("Formular inscriere");
		shlInscriereFacultate.setLayout(null);
		
		txtStudentname = new Text(shlInscriereFacultate, SWT.BORDER);
		txtStudentname.setBounds(206, 31, 349, 21);
		
		Label lblCodNumericPersonal = new Label(shlInscriereFacultate, SWT.NONE);
		lblCodNumericPersonal.setBounds(20, 111, 155, 15);
		lblCodNumericPersonal.setText("Cod numeric personal - CNP:");
		
		Label lblAmAbsolvitCursurile = new Label(shlInscriereFacultate, SWT.NONE);
		lblAmAbsolvitCursurile.setBounds(20, 472, 163, 15);
		lblAmAbsolvitCursurile.setText("Am absolvit cursurile facult\u0103\u0163ii:");
		
		Label lblMedieExamenBacalaureat = new Label(shlInscriereFacultate, SWT.NONE);
		lblMedieExamenBacalaureat.setBounds(20, 507, 141, 15);
		lblMedieExamenBacalaureat.setText("Medie examen bacalaureat");
		
		Label lblNotaLaMatematica = new Label(shlInscriereFacultate, SWT.NONE);
		lblNotaLaMatematica.setBounds(500, 507, 104, 15);
		lblNotaLaMatematica.setText("Nota la matematic\u0103");
		
		Label lblNotaLaInformatica = new Label(shlInscriereFacultate, SWT.NONE);
		lblNotaLaInformatica.setBounds(283, 507, 102, 15);
		lblNotaLaInformatica.setText("Nota la informatic\u0103");
		
		Label lblNumeleiPrenumele = new Label(shlInscriereFacultate, SWT.NONE);
		lblNumeleiPrenumele.setBounds(20, 34, 176, 15);
		lblNumeleiPrenumele.setText("Numele \u015Fi prenumele studentului");
		
		Label lblNumeleDupCstorie = new Label(shlInscriereFacultate, SWT.NONE);
		lblNumeleDupCstorie.setBounds(20, 58, 215, 15);
		lblNumeleDupCstorie.setText("Numele dup\u0103 c\u0103s\u0103torie (dac\u0103 este cazul) ");
		
		txtMariagestudentname = new Text(shlInscriereFacultate, SWT.BORDER);
		txtMariagestudentname.setBounds(255, 55, 349, 21);
		
		Label lblDataNaterii = new Label(shlInscriereFacultate, SWT.NONE);
		lblDataNaterii.setBounds(20, 87, 68, 15);
		lblDataNaterii.setText("Data na\u015Fterii:");
		
		Label lblZiua = new Label(shlInscriereFacultate, SWT.NONE);
		lblZiua.setBounds(95, 87, 21, 15);
		lblZiua.setText("ziua");
		
		txtBirthDay = new Text(shlInscriereFacultate, SWT.BORDER);
		txtBirthDay.setBounds(122, 84, 76, 21);
		
		Label lblLuna = new Label(shlInscriereFacultate, SWT.NONE);
		lblLuna.setBounds(202, 87, 46, 15);
		lblLuna.setText(", luna (ll)");
		
		txtBirthMonth = new Text(shlInscriereFacultate, SWT.BORDER);
		txtBirthMonth.setBounds(254, 84, 76, 21);
		
		Label lblAnul = new Label(shlInscriereFacultate, SWT.NONE);
		lblAnul.setBounds(344, 87, 32, 15);
		lblAnul.setText(", anul ");
		
		txtBirthyear = new Text(shlInscriereFacultate, SWT.BORDER);
		txtBirthyear.setBounds(382, 85, 76, 21);
		
		txtCnp = new Text(shlInscriereFacultate, SWT.BORDER);
		txtCnp.setBounds(193, 108, 265, 21);
		
		Label lblLocalitateaNaterii = new Label(shlInscriereFacultate, SWT.NONE);
		lblLocalitateaNaterii.setBounds(20, 147, 98, 15);
		lblLocalitateaNaterii.setText("Localitatea na\u015Fterii");
		
		txtBirthplace = new Text(shlInscriereFacultate, SWT.BORDER);
		txtBirthplace.setBounds(124, 144, 215, 21);
		
		Label lbljudeul = new Label(shlInscriereFacultate, SWT.NONE);
		lbljudeul.setBounds(345, 147, 43, 15);
		lbljudeul.setText(", jude\u0163ul");
		
		txtBirthcity = new Text(shlInscriereFacultate, SWT.BORDER);
		txtBirthcity.setBounds(408, 144, 222, 21);
		
		Label lblara = new Label(shlInscriereFacultate, SWT.NONE);
		lblara.setBounds(20, 184, 20, 15);
		lblara.setText("\u0163ara");
		
		txtBirthcountry = new Text(shlInscriereFacultate, SWT.BORDER);
		txtBirthcountry.setBounds(67, 181, 222, 21);
		
		Label lblNaionalitatea = new Label(shlInscriereFacultate, SWT.NONE);
		lblNaionalitatea.setBounds(20, 224, 74, 15);
		lblNaionalitatea.setText("Na\u0163ionalitatea");
		
		txtNationality = new Text(shlInscriereFacultate, SWT.BORDER);
		txtNationality.setBounds(105, 221, 225, 21);
		
		Label lblCetenia = new Label(shlInscriereFacultate, SWT.NONE);
		lblCetenia.setBounds(345, 224, 50, 15);
		lblCetenia.setText("Cet\u0103\u0163enia");
		
		txtCitizenship = new Text(shlInscriereFacultate, SWT.BORDER);
		txtCitizenship.setBounds(408, 218, 222, 21);
		txtCitizenship.setToolTipText("");
		
		Label lblDomiciliulStabil = new Label(shlInscriereFacultate, SWT.NONE);
		lblDomiciliulStabil.setBounds(20, 258, 88, 15);
		lblDomiciliulStabil.setText("Domiciliul stabil:");
		
		Label lblLocalitatea = new Label(shlInscriereFacultate, SWT.NONE);
		lblLocalitatea.setBounds(122, 258, 54, 15);
		lblLocalitatea.setText("localitatea");
		
		txtPlace = new Text(shlInscriereFacultate, SWT.BORDER);
		txtPlace.setBounds(193, 258, 183, 21);
		
		Label label = new Label(shlInscriereFacultate, SWT.NONE);
		label.setBounds(386, 258, 43, 15);
		label.setText(", jude\u0163ul");
		
		txtCity = new Text(shlInscriereFacultate, SWT.BORDER);
		txtCity.setBounds(449, 255, 181, 21);
		
		Label lblStrada = new Label(shlInscriereFacultate, SWT.NONE);
		lblStrada.setBounds(20, 292, 32, 15);
		lblStrada.setText("strada");
		
		txtStreet = new Text(shlInscriereFacultate, SWT.BORDER);
		txtStreet.setBounds(71, 289, 158, 21);
		
		Label lblNr = new Label(shlInscriereFacultate, SWT.NONE);
		lblNr.setBounds(231, 292, 20, 15);
		lblNr.setText(", nr.");
		
		Label lblBloc = new Label(shlInscriereFacultate, SWT.NONE);
		lblBloc.setBounds(320, 292, 29, 15);
		lblBloc.setText(", bloc");
		
		Label lblSc = new Label(shlInscriereFacultate, SWT.NONE);
		lblSc.setBounds(420, 292, 20, 15);
		lblSc.setText(", sc.");
		
		Label lblEt = new Label(shlInscriereFacultate, SWT.NONE);
		lblEt.setBounds(511, 292, 19, 15);
		lblEt.setText(", et.");
		
		Label lblAp = new Label(shlInscriereFacultate, SWT.NONE);
		lblAp.setBounds(592, 292, 22, 15);
		lblAp.setText(", ap.");
		
		txtNr = new Text(shlInscriereFacultate, SWT.BORDER);
		txtNr.setBounds(255, 289, 59, 21);
		
		txtBlock = new Text(shlInscriereFacultate, SWT.BORDER);
		txtBlock.setBounds(355, 289, 59, 21);
		
		txtSc = new Text(shlInscriereFacultate, SWT.BORDER);
		txtSc.setBounds(446, 289, 59, 21);
		
		txtEt = new Text(shlInscriereFacultate, SWT.BORDER);
		txtEt.setBounds(536, 289, 50, 21);
		
		txtAp = new Text(shlInscriereFacultate, SWT.BORDER);
		txtAp.setBounds(620, 286, 50, 21);
		
		Label lblAdresEmail = new Label(shlInscriereFacultate, SWT.NONE);
		lblAdresEmail.setBounds(20, 325, 73, 15);
		lblAdresEmail.setText("Adres\u0103 e-mail");
		
		txtEmail = new Text(shlInscriereFacultate, SWT.BORDER);
		txtEmail.setBounds(95, 322, 310, 21);
		
		Label lblNumrDeTelefon = new Label(shlInscriereFacultate, SWT.NONE);
		lblNumrDeTelefon.setBounds(20, 357, 93, 15);
		lblNumrDeTelefon.setText("Num\u0103r de telefon");
		
		txtPhone = new Text(shlInscriereFacultate, SWT.BORDER);
		txtPhone.setBounds(122, 351, 307, 21);
		
		Label lblPrinii = new Label(shlInscriereFacultate, SWT.NONE);
		lblPrinii.setBounds(20, 378, 37, 15);
		lblPrinii.setText("P\u0103rin\u0163ii");
		
		Label lblTataNumelei = new Label(shlInscriereFacultate, SWT.NONE);
		lblTataNumelei.setBounds(79, 378, 139, 15);
		lblTataNumelei.setText("Tata, numele \u015Fi prenumele");
		
		Label lblMamaNumelei = new Label(shlInscriereFacultate, SWT.NONE);
		lblMamaNumelei.setBounds(81, 413, 151, 15);
		lblMamaNumelei.setText("Mama, numele \u015Fi prenumele");
		
		txtFathername = new Text(shlInscriereFacultate, SWT.BORDER);
		txtFathername.setBounds(254, 378, 260, 21);
		
		txtMothername = new Text(shlInscriereFacultate, SWT.BORDER);
		txtMothername.setBounds(254, 410, 260, 21);
		
		Label lblStareaCivilA = new Label(shlInscriereFacultate, SWT.NONE);
		lblStareaCivilA.setBounds(20, 442, 137, 15);
		lblStareaCivilA.setText("Starea civil\u0103 a studentului:");
		
		txtOldfaculty = new Text(shlInscriereFacultate, SWT.BORDER);
		txtOldfaculty.setBounds(203, 469, 327, 21);
		
		txtGradeexam = new Text(shlInscriereFacultate, SWT.BORDER);
		txtGradeexam.setBounds(167, 501, 76, 21);
		
		txtGradeinfo = new Text(shlInscriereFacultate, SWT.BORDER);
		txtGradeinfo.setBounds(394, 501, 76, 21);
		
		txtGrademath = new Text(shlInscriereFacultate, SWT.BORDER);
		txtGrademath.setBounds(620, 501, 76, 21);
		
		Button btnCstorit = new Button(shlInscriereFacultate, SWT.RADIO);
		btnCstorit.setBounds(167, 441, 66, 16);
		btnCstorit.setText("c\u0103s\u0103torit");
		married=btnCstorit.getSelection();
		
		Button btnNecstorit = new Button(shlInscriereFacultate, SWT.RADIO);
		btnNecstorit.setBounds(238, 442, 79, 16);
		btnNecstorit.setText("nec\u0103s\u0103torit");
		notMarried = btnNecstorit.getSelection();
		
		Button btnTrimitereFormular = new Button(shlInscriereFacultate, SWT.NONE);
		btnTrimitereFormular.setBounds(384, 550, 108, 25);
		btnTrimitereFormular.setText("Trimitere formular");
		
		Label lblNotaLaExamenul = new Label(shlInscriereFacultate, SWT.NONE);
		lblNotaLaExamenul.setBounds(20, 539, 163, 15);
		lblNotaLaExamenul.setText("Nota la examenul de admitere");
		
		txtGradeadmexam = new Text(shlInscriereFacultate, SWT.BORDER);
		txtGradeadmexam.setBounds(193, 533, 76, 21);
		btnTrimitereFormular.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetSelected(SelectionEvent e) {
					Student s = createStudent();
					String msg = new String();
					try {
						int style;
						if(ValidateStudent.validate(s)) {
							style = SWT.ICON_ERROR;
							msg="Unele campuri nu au fost completate corect";
						}
						else {
							style = SWT.OK;
							DatabaseOperations.insertStudent(s);
							msg = "Datele au fost memorate";
						}
						MessageBox messageBox = new MessageBox(shlInscriereFacultate, style);
						messageBox.setMessage(msg);
						int rc = messageBox.open();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						System.out.println("Eroare la parsarea datei.");
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					System.out.println("default selection");
					
				}
		});
	}
	
	Student createStudent() {
		Student s = new Student();
		s.setTxtStudentname(txtStudentname.getText()); s.setTxtMariagestudentname(txtMariagestudentname.getText());
		s.setTxtBirthDay(txtBirthDay.getText()); s.setTxtBirthMonth(txtBirthMonth.getText()); s.setTxtBirthyear(txtBirthyear.getText());
		s.setTxtCnp(txtCnp.getText());
		s.setTxtBirthplace(txtBirthplace.getText()); s.setTxtBirthcity(txtBirthcity.getText()); s.setTxtBirthcountry(txtBirthcountry.getText());
		s.setTxtNationality(txtNationality.getText()); s.setTxtCitizenship(txtCitizenship.getText());
		s.setTxtPlace(txtPlace.getText()); s.setTxtCity(txtCity.getText()); s.setTxtStreet(txtStreet.getText());
		s.setTxtNr(txtNr.getText()); s.setTxtBlock(txtBlock.getText()); s.setTxtSc(txtSc.getText());
		s.setTxtEt(txtEt.getText()); s.setTxtAp(txtAp.getText());
		s.setTxtEmail(txtEmail.getText()); s.setTxtPhone(txtPhone.getText());
		s.setTxtFathername(txtFathername.getText()); s.setTxtMothername(txtMothername.getText());
		s.setTxtOldfaculty(txtOldfaculty.getText());
		s.setTxtGradeexam(txtGradeexam.getText()); s.setTxtGradeinfo(txtGradeinfo.getText()); s.setTxtGrademath(txtGrademath.getText());
		s.setMarried(married); s.setNotMarried(notMarried);
		s.setTxtGradeadmExam(txtGradeadmexam.getText());
		return s;
	}
}
