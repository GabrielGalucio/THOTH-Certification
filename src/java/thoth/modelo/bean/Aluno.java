package thoth.modelo.bean;

public class Aluno extends Usuario {
    private long numMatricula;
    
    public Aluno(){
        super();
    }

    public long getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(long numMatricula) {
        this.numMatricula = numMatricula;
    }
                
}
