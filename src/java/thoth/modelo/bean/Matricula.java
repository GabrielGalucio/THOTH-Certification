package thoth.modelo.bean;

import java.sql.Time;
import java.util.Date;

public class Matricula {
    private long codigo;
    private Date data;
    private Time hora;
    private double valor;
    private String status;
    private Curso curso;
    private Aluno aluno;
    private String transacaoPagamento;
    
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getTransacaoPagamento() {
        return transacaoPagamento;
    }

    public void setTransacaoPagamento(String transacaoPagamento) {
        this.transacaoPagamento = transacaoPagamento;
    }

}
