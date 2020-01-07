package thoth.modelo.bean;

public class Funcionario extends Usuario{
    private String cpf;
    private String tipo;
    
    public Funcionario(){
        super();
    }
    
    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
