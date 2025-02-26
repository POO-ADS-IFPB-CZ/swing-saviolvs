package view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaMenu extends JFrame {
    private JPanel contentPane;
    private JButton produtosButton;
    private JButton clientesButton;
    private JButton vendasButton;
    private JButton buttonOK;

    public TelaMenu() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("img/supermercado.png");
        setIconImage(icon.getImage());
        setTitle("Supermercado POO");
        setSize(600,600);
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        produtosButton.addActionListener(e -> {
            TelaCadastroProduto cadastroProduto = new TelaCadastroProduto();
            dispose();
            cadastroProduto.pack();
            cadastroProduto.setLocationRelativeTo(null);
            cadastroProduto.setVisible(true);
            setVisible(true);
        });
        clientesButton.addActionListener(e -> {
            TelaCadastroCliente cadastroCliente = new TelaCadastroCliente();
            dispose();
            cadastroCliente.pack();
            cadastroCliente.setLocationRelativeTo(null);
            cadastroCliente.setVisible(true);
            setVisible(true);
        });
        vendasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entrou "+e.getX()+","+e.getY());
            }
        });
    }

    public static void main(String[] args) {
        TelaMenu dialog = new TelaMenu();
//        dialog.pack();
        dialog.setVisible(true);
    }
}
