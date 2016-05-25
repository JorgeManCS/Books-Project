package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import Controlador.CreadorPDF;
import Controlador.ImportacionCsv;
import Modelo.Libro;
import javax.swing.SwingConstants;


/**
 * @author JorgeManuel
 * @version 1.0
 * Vista del proyecto
 *
 */
public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField textISBN;
	private JTextField textTitle;
	private JTextField textAuthor;
	private JTextField textPublicationYear;
	private JTextField textPublisher;
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblRegistro = new JLabel();
	private String[] cabecera;
	private DefaultTableModel dtm;
	
	//Lista de objetos Libro
	private List<Libro> lista = new ArrayList<>();
	
	//Variable usada para actualizar el registro
	boolean actualizar = true;
	
	//Variable usada para borrar los registros
	boolean borrar = false;
	
	//Variable para controlar el registro en el que nos situamos
	int registro;

	public Vista(){
		//cargamos los componenetes de la vista
		componentes();
		
		tabla.getSelectionModel().addListSelectionListener((ListSelectionEvent e) ->{
			//Añadimos los registros para actualizar los datos a la hora de cambiar la fila
			if (actualizar){
				textISBN.setText(lista.get(tabla.getSelectedRow()).getIsbn());
				textTitle.setText(lista.get(tabla.getSelectedRow()).getBookTitle());
				textAuthor.setText(lista.get(tabla.getSelectedRow()).getBookAuthor());
				textPublicationYear.setText(lista.get(tabla.getSelectedRow()).getBookYear()+"");
				textPublisher.setText(lista.get(tabla.getSelectedRow()).getPublisher());
			}
	
			if(tabla.getSelectedRow() != -1){
				//Contamos el los registros que contiene la tabla
				registro = tabla.getSelectedRow();
				lblRegistro.setText("Fila: "+(tabla.getSelectedRow()+1)+" de "+tabla.getRowCount());
			}
	  });
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private void componentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JFileChooser abrirArchivo = new JFileChooser();
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu menuStart = new JMenu("Start");
		menuBar.add(menuStart);
		
		JMenuItem fUpload = new JMenuItem("File Upload");
		menuStart.add(fUpload);
		
		fUpload.addActionListener(new ActionListener(){

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			//Abrimos los archivos ejecutando el JFilechooser
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int archivo = abrirArchivo.showOpenDialog(contentPane);
				if (archivo==JFileChooser.APPROVE_OPTION){
					lista = ImportacionCsv.registros(abrirArchivo.getSelectedFile());
					tabla.setModel(ImportacionCsv.tablaRegistros(cabecera, lista));
				}
			}
			
		});
		
		JMenuItem ePdf = new JMenuItem("Create PDF");
		menuStart.add(ePdf);
		ePdf.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				generarPdfActionPerformed(evt);
			}
		});
		
		JMenu menuMore = new JMenu("More");
		menuBar.add(menuMore);
		
		JMenuItem about = new JMenuItem("About");
		menuMore.add(about);
		about.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				aboutActionPerformed(evt);
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel1 = new JPanel();
		buttonPanel.add(buttonPanel1, BorderLayout.NORTH);

		
		JButton buttonPrevious = new JButton("Previous");
		buttonPanel1.add(buttonPrevious);
		buttonPrevious.addActionListener (new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previousActionPerformed(evt);				
			}			 
		 });
		
		JButton buttonNext = new JButton("Next");
		buttonPanel1.add(buttonNext);
		buttonNext.addActionListener (new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextActionPerformed(evt);				
			}			 
		 });
		
		JButton buttonNew = new JButton("New");
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newActionPerformed(evt);
			}
		});
		buttonPanel1.add(buttonNew);
		
		JButton buttonDelete = new JButton("Delete");
		buttonPanel1.add(buttonDelete);
		buttonDelete.addActionListener (new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteActionPerformed(evt);				
			}			 
		 });
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.9);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		
		textISBN = new JTextField();
		textISBN.setColumns(10);
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		
		textAuthor = new JTextField();
		textAuthor.setColumns(10);
		
		textPublicationYear = new JTextField();
		textPublicationYear.setColumns(10);
		
		textPublisher = new JTextField();
		textPublisher.setColumns(10);
		
		cabecera = new String[] {"ISBN", "Title", "Author", "Year of publication", "Publisher"};
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(cabecera);
		
		tabla= new JTable(dtm);
		scrollPane.setViewportView(tabla);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		
		JLabel labelISBN = new JLabel("ISBN");
		textISBN.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				celdaIsbnActionPerformed(evt);				
			}			 
		 });
		
		
		
		JLabel labelTitle = new JLabel("TITLE");
		textTitle.addActionListener (new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				celdaTitleActionPerformed(evt);				
			}			 
		 });
		
		
		JLabel labelAuthor = new JLabel("AUTHOR");
		textAuthor.addActionListener (new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				celdaAuthorActionPerformed(evt);				
			}			 
		 });
		
		JLabel labelPublicationYear = new JLabel("PUBLICATION YEAR");
		textAuthor.addActionListener (new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				celdaYearActionPerformed(evt);				
			}			 
		 });
		
		
		
		JLabel labelPublisher = new JLabel("PUBLISHER");
		textPublisher.addActionListener (new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				celdaPublisherActionPerformed(evt);				
			}			 
		 });
		
		
		
		
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(labelISBN)
							.addPreferredGap(ComponentPlacement.RELATED, 321, Short.MAX_VALUE))
						.addComponent(textISBN, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addComponent(labelTitle)
						.addComponent(textTitle, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addComponent(textAuthor, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addComponent(labelAuthor)
						.addComponent(labelPublicationYear, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPublicationYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPublisher, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addComponent(labelPublisher, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRegistro))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(labelISBN)
					.addGap(1)
					.addComponent(textISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelTitle)
					.addGap(2)
					.addComponent(textTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelAuthor)
					.addGap(1)
					.addComponent(textAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelPublicationYear)
					.addGap(1)
					.addComponent(textPublicationYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelPublisher)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPublisher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
					.addComponent(lblRegistro)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
	}
	
	//Aqui empiezan los metodos.
	
	//Añade ISBN a la celda correspondiente
	private void celdaIsbnActionPerformed (java.awt.event.ActionEvent evt){
		int fila = tabla.getSelectedRow();
		lista.get(tabla.getSelectedRow()).setIsbn(textISBN.getText());
		actualizar = false;
		tabla.setModel(ImportacionCsv.tablaRegistros(cabecera, lista));
		actualizar = true;
		tabla.setRowSelectionInterval(fila, fila);
	}
	
	//Añade Titulo a la celda correspondiente
	private void celdaTitleActionPerformed (java.awt.event.ActionEvent evt){
		int fila = tabla.getSelectedRow();
		lista.get(tabla.getSelectedRow()).setIsbn(textTitle.getText());
		actualizar = false;
		tabla.setModel(ImportacionCsv.tablaRegistros(cabecera, lista));
		actualizar = true;
		tabla.setRowSelectionInterval(fila, fila);
	}
	
	//Añade Autor a la celda correspondiente
	private void celdaAuthorActionPerformed (java.awt.event.ActionEvent evt){
		int fila = tabla.getSelectedRow();
		lista.get(tabla.getSelectedRow()).setIsbn(textAuthor.getText());
		actualizar = false;
		tabla.setModel(ImportacionCsv.tablaRegistros(cabecera, lista));
		actualizar = true;
		tabla.setRowSelectionInterval(fila, fila);
	}
	
	//Añade Año a la celda correspondiente
	private void celdaYearActionPerformed (java.awt.event.ActionEvent evt){
		int fila = tabla.getSelectedRow();
		lista.get(tabla.getSelectedRow()).setIsbn(textPublicationYear.getText());
		actualizar = false;
		tabla.setModel(ImportacionCsv.tablaRegistros(cabecera, lista));
		actualizar = true;
		tabla.setRowSelectionInterval(fila, fila);
	}
	
	//Añade Publisher a la celda correspondiente
	private void celdaPublisherActionPerformed (java.awt.event.ActionEvent evt){
		int fila = tabla.getSelectedRow();
		lista.get(tabla.getSelectedRow()).setIsbn(textPublisher.getText());
		actualizar = false;
		tabla.setModel(ImportacionCsv.tablaRegistros(cabecera, lista));
		actualizar = true;
		tabla.setRowSelectionInterval(fila, fila);
	}

	//Metodo para poder pasar al siguiente campo
	private void nextActionPerformed(java.awt.event.ActionEvent evt){
		 if(tabla.getSelectedRow()!=tabla.getRowCount()-1 && tabla.getSelectedRow()!=-1)
			 tabla.setRowSelectionInterval(tabla.getSelectedRow()+1,tabla.getSelectedRow()+1);
		 else if(tabla.getSelectedRow()==tabla.getRowCount()-1 && tabla.getSelectedRow()!=-1){
	            tabla.setRowSelectionInterval(0, 0);
	            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());        
	        }
	 }
	
	//Metodo para poder pasar al campo anterior
	 private void previousActionPerformed(java.awt.event.ActionEvent evt){
		 if (tabla.getSelectedRow()!=0 && tabla.getSelectedRow()!=-1)
	            tabla.setRowSelectionInterval(tabla.getSelectedRow()-1, tabla.getSelectedRow()-1);
	        else if (tabla.getSelectedRow()==0){
	            tabla.setRowSelectionInterval(tabla.getRowCount()-1, tabla.getRowCount()-1);
	            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	        }	  
	 }
	 
	//Metodo para poder eliminar un campo
	 private void deleteActionPerformed(java.awt.event.ActionEvent evt){
		 actualizar =false;
		 borrar = true;
		 //Para ello, hace uso del metodo creado en "ImportacionCsv"
		 tabla.setModel(ImportacionCsv.eliminarRegistro(cabecera, lista, tabla.getSelectedRow()));
		 actualizar = true;
		 if(tabla.getRowCount() !=0){
			 tabla.setRowSelectionInterval(registro, registro);
		 }
		 borrar = false;
	 }
	 
	//Metodo para poder crear un campo nuevo
	 private void newActionPerformed(java.awt.event.ActionEvent evt){
		 actualizar =false;
		//Para ello, hace uso del metodo creado en "ImportacionCsv"
		 tabla.setModel(ImportacionCsv.anadirRegistro(cabecera,lista));	 
		 actualizar = true;
		 tabla.setRowSelectionInterval(tabla.getRowCount()-1,tabla.getRowCount()-1);
	 }
	 
	 //Metodo para generar el PDF, extraido de la clase "CreadorPDF"
	 private void generarPdfActionPerformed(java.awt.event.ActionEvent evt){
		 CreadorPDF.FabricarPdf(lista);
	 }
	 
	 //Metodo para mostrar un JOptionPane
	 private void aboutActionPerformed(java.awt.event.ActionEvent evt){
			JOptionPane.showMessageDialog(rootPane, "Proyecto Tablas de libros\n"
					+ "Virgen del Carmen 2015/2016\n"
					+ "Jorge Manuel Castillo Sánchez","About: ", JOptionPane.PLAIN_MESSAGE);
		}

}
