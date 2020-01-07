package thoth.modelo.bean;

import java.sql.Time;
import java.util.Date;

public class PeriodoCurso {
    private long codigo;
    private Date dtInicio;
    private Time hrInicio;
    private Time hrFinal;
    private Curso curso;

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
     * @return the dtInicio
     */
    public Date getDtInicio() {
        return dtInicio;
    }

    /**
     * @param dtInicio the dtInicio to set
     */
    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    /**
     * @return the hrInicio
     */
    public Time getHrInicio() {
        return hrInicio;
    }

    /**
     * @param hrInicio the hrInicio to set
     */
    public void setHrInicio(Time hrInicio) {
        this.hrInicio = hrInicio;
    }

    /**
     * @return the hrFinal
     */
    public Time getHrFinal() {
        return hrFinal;
    }

    /**
     * @param hrFinal the hrFinal to set
     */
    public void setHrFinal(Time hrFinal) {
        this.hrFinal = hrFinal;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
}
