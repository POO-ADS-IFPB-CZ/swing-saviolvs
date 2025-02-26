package dao;

import model.Produto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        try {
            ProdutoDaoBanco dao = new ProdutoDaoBanco();
            System.out.println(dao.getProdutos());
//            dao.adicionarProduto(new Produto(1, "Arroz",
//                    5.5f, LocalDate.of(2025,7,6)));
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
