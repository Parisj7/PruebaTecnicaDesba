package Project.PruebaTecnicaFrancoParis.Model;

import Project.PruebaTecnicaFrancoParis.Reader.CSVReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GraphicInterface extends JFrame {          //Aqui tenemos la creacion de toda la interfaz grafica

    public JPanel panel;                                // Atributos que, como son necesarios en mas de un boton,
    public JLabel labelPath;                            // tienen un scope general
    public JLabel labelError;
    public String fileLocation ;

    public GraphicInterface(){                          //creacion de la interfaz como tal
        setSize(500,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Franco Paris Prueba Técnica");
        setLocationRelativeTo(null);
        components(fileLocation);
    }

    private void components(String fileLocation){       //Metodo para juntar todos los componentes, comodidad personal
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

    private void labelPath(){                           //Este es el label en el que estara la ruta seleccionada
        labelPath = new JLabel("");
        labelPath.setBounds(170,10,275,20);
        panel.add(labelPath);
    }

    private void labelError(){                          //Label que indicara si hubo o no errores al cargar el archivo
        labelError = new JLabel();
        labelError.setBounds(10,100,300,25);
        panel.add(labelError);
    }

    private JButton buttonDefault(){                    //Constructor de un boton para uso general
        JButton button = new JButton();
        panel.add(button);
        return button;
    }

    private void buttonSelect(){                        //boton para seleccinar el archivo
        JButton buttonSelect = new JButton("Seleccione Archivo");
        buttonSelect.setBounds(10,10,150,25);
        buttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jFileChooser = new JFileChooser();         // Selector del archivo
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "CSV archivo (* CSV)", "CSV");
                jFileChooser.setFileFilter(filter);
                int file = jFileChooser.showOpenDialog(GraphicInterface.this);
                if (file != JFileChooser.CANCEL_OPTION){
                    File fileName = jFileChooser.getSelectedFile();

                    fileLocation=fileName.getAbsolutePath();                                            //Captura de la ruta del archivo para uso general
                    fileLocation = fileLocation.replaceAll("\\\\","\\\\\\\\");          //La ruta capturada por default viene con una
                    labelPath.setText(fileLocation);                                                    // sola barra "\" y para ser usada la sintaxis
                    System.out.println(fileLocation);                                                   // la sintaxis requiere 2, y curiosamente el
                }                                                                                       // metodo replaceAll requiere de 4 barras dentro
            }                                                                                           // del string para valer 1 sola por fuera
        });
        panel.add(buttonSelect);
    }

    private void buttonSave(){                                                  //Boton que realizara la carga de datos
        JButton buttonSave = new JButton("GRABAR CAMBIO");
        buttonSave.setBounds(200,170,150,25);
        buttonSave.setBackground(Color.green);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileLocation == null || fileLocation == ""){                        //Si no se ingreso Path o el mismo se borro
                    labelPath.setText("<-- Por favor seleccione aqui un archivo ");     // con el boton cancelar nos sera indicado
                }                                                                       // en el label aqui
                else {
                    CSVReader csvReader = new CSVReader();                              //Llamado al metodo que realizara la carga
                    csvReader.reader(fileLocation);
                    if (csvReader.showErrorCounter() > 0) {                             //Si ocurrieron errores se nos indicara y se
                        labelError.setBounds(10,100,400,25);          // creara un boton donde podamos leer los mismo
                        labelError.setText("Ocurrieron errores durante la ultima carga del archivo");
                        JButton buttonlog = buttonDefault();
                        buttonlog.setText("ver errores");
                        buttonlog.setBounds(350,100,100,25);
                        buttonlog.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Runtime obj = Runtime.getRuntime();
                                    obj.exec("notepad "+System.getProperty("user.dir")+"\\avisos.log"); //La instrucción es notepad para abrir el bloc de notas,
                                } catch (IOException ex) {                                                       // Obtiene la ruta del proyecto, y indicamos el archivo
                                    System.out.println("IOException "+ex.getMessage());                          // que esta guardando los errores
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

    private void buttonCancel(){                                    //Boton cancelar, el mismo nos reinicia el programa
        JButton buttonCancel = new JButton("CANCELAR");         // NO REALIZA UN ROLLBACK a la base de datos (a peticion del usuario)
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
}