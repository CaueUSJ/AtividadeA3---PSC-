
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioFormDialog extends JDialog {

    private JTextField txtRegistro;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public UsuarioFormDialog(Frame parent) {
        super(parent, "Cadastro de Usuário", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtRegistro = new JTextField(20);
        txtNome = new JTextField(20);
        txtEndereco = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtEmail = new JTextField(20);

        painelCampos.add(new JLabel("Registro:"));
        painelCampos.add(txtRegistro);
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Endereço:"));
        painelCampos.add(txtEndereco);
        painelCampos.add(new JLabel("Telefone:"));
        painelCampos.add(txtTelefone);
        painelCampos.add(new JLabel("Email:"));
        painelCampos.add(txtEmail);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Evento de clique no botão salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
            }
        });

        // Evento cancelar
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void salvarUsuario() {
        String registro = txtRegistro.getText();
        String nome = txtNome.getText();
        String endereco = txtEndereco.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();

        // Aqui você pode chamar um método do DAO para inserir no banco
        // Exemplo fictício:
        // UsuarioDAO.inserir(new Usuario(registro, nome, endereco, telefone, email));

        JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
        dispose();
    }
}