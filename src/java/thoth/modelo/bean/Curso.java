package thoth.modelo.bean;

public class Curso {
    private long codigo;
    private String titulo;
    private String status;
    private String objetivo; 
    private String ementa;
    private String bibliografia;
    private double valor;
    private short cargaHr;
    private String metodologia;
    private String area;
    private Localizacao local;
    private String evento;
    private Funcionario funcionario;
    private short qtdeVaga;
    private Professor professor;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
 
    /**
     * @return the ementa
     */
    public String getEmenta() {
        return ementa;
    }

    /**
     * @param ementa the ementa to set
     */
    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    /**
     * @return the bibliografia
     */
    public String getBibliografia() {
        return bibliografia;
    }

    /**
     * @param bibliografia the bibliografia to set
     */
    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the cargaHr
     */
    public short getCargaHr() {
        return cargaHr;
    }

    /**
     * @param cargaHr the cargaHr to set
     */
    public void setCargaHr(short cargaHr) {
        this.cargaHr = cargaHr;
    }

    /**
     * @return the metodologia
     */
    public String getMetodologia() {
        return metodologia;
    }

    /**
     * @param metodologia the metodologia to set
     */
    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the local
     */
    public Localizacao getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(Localizacao local) {
        this.local = local;
    }

    /**
     * @return the tipoEvento
     */
    public String getEvento() {
        return evento;
    }

    /**
     * @param evento
     */
    public void setEvento(String evento) {
        this.evento = evento;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the qtdeVaga
     */
    public short getQtdeVaga() {
        return qtdeVaga;
    }

    /**
     * @param qtdeVaga the qtdeVaga to set
     */
    public void setQtdeVaga(short qtdeVaga) {
        this.qtdeVaga = qtdeVaga;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
}
