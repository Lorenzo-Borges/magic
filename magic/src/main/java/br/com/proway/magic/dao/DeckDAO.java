/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.proway.magic.dao;

import br.com.proway.magic.conexao.Conexao;
import br.com.proway.magic.modelo.Deck;
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
public class DeckDAO {
    
     public void inserir(Deck deck) throws SQLException {
        String sql = "INSERT INTO colecoes (quantidade_cartas, cores, permitido) VALUES (?, ?, ?)";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setInt(1, deck.getQuantidadeCartas());
            executor.setString(2, deck.getCores());
            executor.setBoolean(3, deck.isPermitido());
            executor.executeUpdate();
        }
    }
    
    public ArrayList<Deck> consultarTodos() throws SQLException {
        String sql = "SELECT id, nome, ano FROM colecoes ORDER BY id";
        try(Connection conexao = Conexao.abrir()){
            Statement executor = conexao.createStatement();
            executor.execute(sql);
            
            ResultSet dados = executor.getResultSet();
            
        ArrayList<Deck> colecoes = new ArrayList<>();
        while (dados.next()) {
            Deck colecao = new Deck();
            colecao.setId(dados.getInt(1));
            colecao.setQuantidadeCartas(dados.getInt(2));
            colecao.setCores(dados.getString(3));
            colecao.setPermitido(dados.getBoolean(4));
            
            colecoes.add(colecao);
    }
        return colecoes;
        }
    }
    
    public void delete(Deck colecao) throws SQLException {
        String sql = "DELETE FROM colecoes WHERE id=?";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setInt(1, colecao.getId());
            executor.executeUpdate();
        }
    }
    
    public void editar(Deck colecao) throws SQLException {
        String sql = "UPDATE colecoes SET quantidade_cartas=?, cores=?, permitido=? WHERE id=?";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setInt(1, colecao.getQuantidadeCartas());
            executor.setString(2, colecao.getCores());
            executor.setBoolean(3, colecao.isPermitido());
            executor.setInt(4, colecao.getId());
            executor.executeUpdate();
        }
    }
    
}
