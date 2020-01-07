package thoth.modelo.bean;

import java.util.Date;

public class Frequencia {
    private long codigo;
    private String status;
    private Matricula matricula;
    private Date data_frequencia;

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setData_frequencia(Date data_frequencia) {
        this.data_frequencia = data_frequencia;
    }

    public Date getData_frequencia() {
        return data_frequencia;
    }
    
  
}
