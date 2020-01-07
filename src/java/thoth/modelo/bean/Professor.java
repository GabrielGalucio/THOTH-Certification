package thoth.modelo.bean;

public class Professor extends Usuario{
    private String currilo;
    private String especialidade;
    private long cd_equipe;
    private String equipe;

    public Professor(){
        super();
    }
    
    /**
     * @return the currilo
     */
    public String getCurrilo() {
        return currilo;
    }

    /**
     * @param currilo the currilo to set
     */
    public void setCurrilo(String currilo) {
        this.currilo = currilo;
    }

    /**
     * @return the especialidade
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * @param especialidade the especialidade to set
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
     public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getEquipe() {
        return equipe;
    }

    public long getCd_equipe() {
        return cd_equipe;
    }

    public void setCd_equipe(long cd_equipe) {
        this.cd_equipe = cd_equipe;
    }
    
    
}
