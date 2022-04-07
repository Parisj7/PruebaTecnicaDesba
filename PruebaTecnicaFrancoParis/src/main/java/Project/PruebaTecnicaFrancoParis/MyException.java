package Project.PruebaTecnicaFrancoParis;

public class MyException extends Exception{                     //Excepciones creadas para marcar errores no vitales
    private int errorCode;                                      // para el programa pero que causan una erronea carga de datos

    public MyException(int errorCode){
        super();
        this.errorCode=errorCode;
    }

    @Override
    public String getMessage(){
        String message="";
        switch(errorCode){
            case 1:
                message="Falta la marca del producto";
                break;
            case 2:
                message="Falta la descripcion del producto en la linea";
                break;
        }

        return message;
    }

}
