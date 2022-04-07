package Project.PruebaTecnicaFrancoParis.Model;

import Project.PruebaTecnicaFrancoParis.Reader.CSVReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GraphicInterface extends JFrame {

    public JPanel panel;
    public JLabel labelPath;
    public JLabel labelError;
    public String fileLocation ;

    public GraphicInterface(){
        setSize(500,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Franco Paris Prueba Técnica");
        setLocationRelativeTo(null);
        components(fileLocation);
    }

    private void components(String fileLocation){
        panel();
        buttonSelect();
        buttonSave();
        buttonCancel();
        labelPath();
        labelError();
    }

    private void panel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }

    private void labelPath(){
        labelPath = new JLabel("");
        labelPath.setBounds(170,10,275,20);
        panel.add(labelPath);
    }

    private void labelError(){
        labelError = new JLabel();
        labelError.setBounds(10,100,300,25);
        panel.add(labelError);
    }

    private JButton buttonDefault(){
        JButton button = new JButton();
        panel.add(button);
        return button;
    }

    private void buttonSelect(){
        JButton buttonSelect = new JButton("Seleccione Archivo");
        buttonSelect.setBounds(10,10,150,25);
        buttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "CSV archivo (* CSV)", "CSV");
                jFileChooser.setFileFilter(filter);
                int file = jFileChooser.showOpenDialog(GraphicInterface.this);
                if (file != JFileChooser.CANCEL_OPTION){
                    File fileName = jFileChooser.getSelectedFile();

                    fileLocation=fileName.getAbsolutePath();
                    fileLocation = fileLocation.replaceAll("\\\\","\\\\\\\\");
                    labelPath.setText(fileLocation);
                    System.out.println(fileLocation);
                }
            }
        });
        panel.add(buttonSelect);
    }

    private void buttonSave(){
        JButton buttonSave = new JButton("GRABAR CAMBIO");
        buttonSave.setBounds(200,170,150,25);
        buttonSave.setBackground(Color.green);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileLocation == null || fileLocation == ""){
                    labelPath.setText("<-- Por favor seleccione aqui un archivo ");
                }
                else {
                    CSVReader csvReader = new CSVReader();
                    csvReader.reader(fileLocation);
                    if (csvReader.showErrorCounter() > 0) {
                        labelError.setBounds(10,100,400,25);
                        labelError.setText("Ocurrieron errores durante la ultima carga del archivo");
                        JButton buttonlog = buttonDefault();
                        buttonlog.setText("ver errores");
                        buttonlog.setBounds(350,100,100,25);
                        buttonlog.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Runtime obj = Runtime.getRuntime();
                                    //La instrucción es notepad para abrir el bloc de notas, espacio
                                    //y la ruta donde esta el archivo
                                    obj.exec("notepad "+System.getProperty("user.dir")+"\\avisos.log");
                                    //Pueden usar System.getProperty("user.dir") Obtiene la ruta del proyecto
                                } catch (IOException ex) {
                                    System.out.println("IOException "+ex.getMessage());
                                }
                            }
                        });
                    }
                    else {
                        labelError.setText("Carga exitosa sin errores");
                    }
                }
            }
        });

        panel.add(buttonSave);

    }

    private void buttonCancel(){
        JButton buttonCancel = new JButton("CANCELAR");
        buttonCancel.setBounds(365,170,100,25);
        buttonCancel.setBackground(Color.red);
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            fileLocation="";
                labelPath.setText("");
            }
        });
        panel.add(buttonCancel);
    }
    private void textBox(){
        JTextField textBox = new JTextField();
        textBox.setBounds(175,30,250,25);
        panel.add(textBox);
    }
}