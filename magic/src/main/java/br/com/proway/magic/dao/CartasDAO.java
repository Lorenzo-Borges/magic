/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.proway.magic.dao;

import br.com.proway.magic.conexao.Conexao;
import br.com.proway.magic.modelo.Carta;
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
public class CartasDAO {
    
    public void inserir(Carta colecao) throws SQLException {
        String sql = "INSERT INTO colecoes (nome, tipo, cor, preco, ataque, resistencia) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setString(1, colecao.getNome());
            executor.setString(2, colecao.getTipo());
            executor.setString(3, colecao.getCor());
            executor.setDouble(4, colecao.getPreco());
            executor.setInt(5, colecao.getAtaque());
            executor.setInt(6, colecao.getResistencia());
            executor.executeUpdate();
        }
    }
    
    public ArrayList<Carta> consultarTodos() throws SQLException {
        String sql = "SELECT id, nome, tipo, cor, preco, ataque, resistencia FROM colecoes ORDER BY id";
        try(Connection conexao = Conexao.abrir()){
            Statement executor = conexao.createStatement();
            executor.execute(sql);
            
            ResultSet dados = executor.getResultSet();
            
        ArrayList<Carta> colecoes = new ArrayList<>();
        while (dados.next()) {
            Carta colecao = new Carta();
            colecao.setId(dados.getInt(1));
            colecao.setNome(dados.getString(2));
            colecao.setTipo(dados.getString(3));
            colecao.setCor(dados.getString(4));
            colecao.setPreco(dados.getDouble(5));
            colecao.setAtaque(dados.getInt(6));
            colecao.setResistencia(dados.getInt(7));
            
            colecoes.add(colecao);
        }
        return colecoes;
        }
    }
    
    public void delete(Carta colecao) throws SQLException {
        String sql = "DELETE FROM colecoes WHERE id=?";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setInt(1, colecao.getId());
            executor.executeUpdate();
        }
    }
    
    public void editar(Carta colecao) throws SQLException {
        String sql = "UPDATE colecoes SET nome=? tipo=? cor=? preco=? ataque=? resistencia=? WHERE id=?)";
        
        try(Connection conexao = Conexao.abrir()){
            PreparedStatement executor = conexao.prepareStatement(sql);
            executor.setString(1, colecao.getNome());
            executor.setString(2, colecao.getTipo());
            executor.setString(3, colecao.getCor());
            executor.setDouble(4, colecao.getPreco());
            executor.setInt(5, colecao.getAtaque());
            executor.setInt(6, colecao.getResistencia());
            executor.setInt(7, colecao.getId());
            executor.executeUpdate();
        }
    }
    
}
