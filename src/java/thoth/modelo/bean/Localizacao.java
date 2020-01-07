package thoth.modelo.bean;

public class Localizacao {
    private long codigo;
    private int qtdeVaga;
    private String nome;
    private String cep;
    private String logradouro;
    private String bairro;
    private short numero;

    /**
     * @return the codigo
     */
    public long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the qtdeVaga
     */
    public int getQtdeVaga() {
        return qtdeVaga;
    }

    /**
     * @param qtdeVaga the qtdeVaga to set
     */
    public void setQtdeVaga(int qtdeVaga) {
        this.qtdeVaga = qtdeVaga;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the numero
     */
    public short getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(short numero) {
        this.numero = numero;
    }
    
    public String toString(){
        return nome;
    }
}
