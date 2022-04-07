package Project.PruebaTecnicaFrancoParis;

public class MyException extends Exception{
    private int errorCode;

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
