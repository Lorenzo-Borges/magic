/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.proway.magic.dao;

import br.com.proway.magic.conexao.Conexao;
import br.com.proway.magic.modelo.Colecao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 120418
 */
public class ColecaoDAO {
    
    public void inserir(Colecao colecao) throws SQLException {
        String sql = "INSERT INTO colecoes (nome, ano) VALUES (?, ?)";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setString(1, colecao.getNome());
            executor.setInt(2, colecao.getAno());
            executor.executeUpdate();
        }
    }
    
    public ArrayList<Colecao> consultarTodos() throws SQLException {
        String sql = "SELECT id, nome, ano FROM colecoes ORDER BY id";
        try(Connection conexao = Conexao.abrir()){
            Statement executor = conexao.createStatement();
            executor.execute(sql);
            
            ResultSet dados = executor.getResultSet();
            
        ArrayList<Colecao> colecoes = new ArrayList<>();
        while (dados.next()) {
            Colecao colecao = new Colecao();
            colecao.setId(dados.getInt(1));
            colecao.setNome(dados.getString(2));
            colecao.setAno(dados.getInt(3));
            
            colecoes.add(colecao);
    }
        return colecoes;
        }
    }
    
    public void delete(Colecao colecao) throws SQLException {
        String sql = "DELETE FROM colecoes WHERE id=?";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setInt(1, colecao.getId());
            executor.executeUpdate();
        }
    }
    
    public void editar(Colecao colecao) throws SQLException {
        String sql = "UPDATE colecoes SET nome=? ano=? WHERE id=?)";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setString(1, colecao.getNome());
            executor.setInt(2, colecao.getAno());
            executor.executeUpdate();
        }
    }
    
}
