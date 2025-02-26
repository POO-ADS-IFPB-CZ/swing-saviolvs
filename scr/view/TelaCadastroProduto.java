package view;

import dao.ProdutoDao;
import model.Produto;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class TelaCadastroProduto extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label1;
    private JTextField campoCodigo;
    private JTextField campoDescricao;
    private JTextField campoPreco;
    private JButton listarButton;
    private JFormattedTextField formattedTextField1;
    private ProdutoDao produtoDao;

    public TelaCadastroProduto() {
        produtoDao = new ProdutoDao();
        setContentPane(contentPane);
        setModal(true);
        setTitle("Cadastro de Produtos");
        ImageIcon icon = new ImageIcon("img/supermercado.png");
        setIconImage(icon.getImage());
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){
                    String codigoString = campoCodigo.getText();
                    int codigo = Integer.parseInt(codigoString);
                    String descricao = campoDescricao.getText();
                    String precoString = campoPreco.getText();
                    float preco = Float.parseFloat(precoString);
                    String validadeString = formattedTextField1.getText();
                    LocalDate validade = LocalDate.parse(validadeString);
                    Produto produto = new Produto(codigo, descricao, preco,
                            validade);

                    try {
                        if(produtoDao.adicionarProduto(produto)){
                            JOptionPane.showMessageDialog(null,
                                    "Salvo com sucesso");
                        }else{
                            //Fazer atualização aqui
                            JOptionPane.showMessageDialog(null,
                                    "Código já existente");
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Falha na conexão com arquivo");
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Falha na conexão com arquivo");
                    }

                }

            }

            private boolean validarCampos() {
                //TODO: Validar os outros campos
                if(campoCodigo.getText().isEmpty() ||
                        campoDescricao.getText().isEmpty() ||
                        formattedTextField1.getText().isEmpty() ||
                        campoPreco.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Preencha todos os campos");
                    return false;
                }
                return true;
            }
        });
        listarButton.addActionListener(e -> {
            TelaVisualizarProdutos visualizarProdutos = new TelaVisualizarProdutos();
            visualizarProdutos.pack();
            visualizarProdutos.setLocationRelativeTo(null);
            visualizarProdutos.setVisible(true);
        });
        buttonCancel.addActionListener(e -> {
            String codigoString = campoCodigo.getText();
            int codigo = Integer.parseInt(codigoString);
            Produto produto = new Produto(codigo, null,
                    0, null);
            try {
                if(produtoDao.removerProduto(produto)){
                    JOptionPane.showMessageDialog(null,
                            "Removido com sucesso");
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Código não existe");
                }
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null,
                        "Falha na conexão com o arquivo");
            }
        });
    }

    private void createUIComponents() {
        formattedTextField1 = new JFormattedTextField();
        try {
            MaskFormatter formatter = new MaskFormatter("####-##-##");
            formatter.install(formattedTextField1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        ImageIcon icon = new ImageIcon("img/supermercado.png");
        label1 = new JLabel();
        label1.setIcon(icon);

        buttonOK = new JButton();
        ImageIcon icon2 = new ImageIcon("img/salvar.png");
        buttonOK.setIcon(icon2);

        buttonCancel = new JButton();
        ImageIcon icon3 = new ImageIcon("img/lixeira.png");
        buttonCancel.setIcon(icon3);
    }
}
