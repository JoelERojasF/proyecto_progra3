/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Entidades.*;
import Persistencia.PersistenciaFachada;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author giova
 */
public class frameMain extends JFrame {
    private JPanel panelCentral;
    private final PersistenciaFachada persistencia;
    
    class PanelPortada extends JPanel {
        private Image imagen;

        public PanelPortada() {
            try {
                imagen = ImageIO.read(new File("imagenes/fondoPrincipal.png"));
            } catch (IOException e) {
                System.out.println("No se pudo cargar la imagen: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public frameMain() {
        persistencia = new PersistenciaFachada();

        setTitle("Bienvenido al Sistema Clinico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra de menú
        JMenuBar menuBar = new JMenuBar();

        // =========================
        // Menú Pacientes
        // =========================
        JMenu menuPacientes = new JMenu("Pacientes");
        JMenuItem opcionAgregarPacientes = new JMenuItem("Agregar pacientes");
        JMenuItem opcionBuscarPacientes = new JMenuItem("Buscar pacientes");
        JMenuItem opcionActualizarPacientes = new JMenuItem("Actualizar pacientes");
        JMenuItem opcionEliminarPacientes = new JMenuItem("Eliminar pacientes");
        JMenuItem opcionListarPacientes = new JMenuItem("Listar pacientes");

        menuPacientes.add(opcionAgregarPacientes);
        menuPacientes.add(opcionBuscarPacientes);
        menuPacientes.add(opcionActualizarPacientes);
        menuPacientes.add(opcionEliminarPacientes);
        menuPacientes.add(opcionListarPacientes);
        menuBar.add(menuPacientes);

        // =========================
        // Menú Médicos
        // =========================
        JMenu menuMedicos = new JMenu("Médicos");
        JMenuItem opcionAgregarMedicos = new JMenuItem("Agregar médicos");
        JMenuItem opcionConsultarMedicos = new JMenuItem("Consultar médicos");

        menuMedicos.add(opcionAgregarMedicos);
        menuMedicos.add(opcionConsultarMedicos);
        menuBar.add(menuMedicos);

        // =========================
        // Menú Equipo Médico
        // =========================
        JMenu menuEquipo = new JMenu("Equipo médico");
        JMenuItem opcionListarEquipo = new JMenuItem("Inventario equipo médico");
        JMenuItem opcionAgregarEquipo = new JMenuItem("Agregar equipo médico");
        JMenuItem opcionEliminarEquipo = new JMenuItem("Eliminar equipo médico");

        menuEquipo.add(opcionListarEquipo);
        menuEquipo.add(opcionAgregarEquipo);
        menuEquipo.add(opcionEliminarEquipo);
        menuBar.add(menuEquipo);

        // =========================
        // Menú Consultas
        // =========================
        JMenu menuConsultas = new JMenu("Consultas");
        JMenuItem opcionListarConsultas = new JMenuItem("Listar consultas médicas");
        JMenuItem opcionAgregarConsultas = new JMenuItem("Agendar consulta médica");
        JMenuItem opcionEliminarConsultas = new JMenuItem("Cancelar consulta médica");

        menuConsultas.add(opcionListarConsultas);
        menuConsultas.add(opcionAgregarConsultas);
        menuConsultas.add(opcionEliminarConsultas);
        menuBar.add(menuConsultas);

        // =========================
        // Menú Reportes
        // =========================
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem opcionReportePacientes = new JMenuItem("Reporte de pacientes");
        JMenuItem opcionReporteMedicos = new JMenuItem("Reporte de médicos");
        JMenuItem opcionReporteEquipos = new JMenuItem("Reporte de equipos médicos");
        JMenuItem opcionReporteConsultas = new JMenuItem("Reporte de consultas");

        menuReportes.add(opcionReportePacientes);
        menuReportes.add(opcionReporteMedicos);
        menuReportes.add(opcionReporteEquipos);
        menuReportes.add(opcionReporteConsultas);
        menuBar.add(menuReportes);

        // =========================
        // Menú Salir
        // =========================
        JMenu menuSalir = new JMenu("Salir");
        JMenuItem opcionSalir = new JMenuItem("Salir");
        menuSalir.add(opcionSalir);
        menuBar.add(menuSalir);

        setJMenuBar(menuBar);

        // =========================
        // Panel central inicial
        // =========================
        panelCentral = new PanelPortada();
        add(panelCentral, BorderLayout.CENTER);


        // =========================
        // Listeners para cambiar panel
        // =========================
        opcionAgregarPacientes.addActionListener(e -> cambiarPanel(new PanelAgregarPacientes())); 
        opcionBuscarPacientes.addActionListener(e -> cambiarPanel(new PanelConsultarPacientes())); 
        opcionActualizarPacientes.addActionListener(e -> cambiarPanel(new PanelActualizarPacientes())); 
        opcionEliminarPacientes.addActionListener(e -> cambiarPanel(new PanelEliminarPacientes())); 
        opcionListarPacientes.addActionListener(e -> cambiarPanel(new PanelListarPacientes())); //acomodar mejor

        opcionAgregarMedicos.addActionListener(e -> cambiarPanel(new PanelAgregarMedicos())); //hacer mas compacto
        opcionConsultarMedicos.addActionListener(e -> cambiarPanel(new PanelConsultarMedicos())); 

        opcionListarEquipo.addActionListener(e -> cambiarPanel(new PanelListarEquipo())); 
        opcionAgregarEquipo.addActionListener(e -> cambiarPanel(new PanelInventariarEquipo())); //hacer mas compacto
        opcionEliminarEquipo.addActionListener(e -> cambiarPanel(new PanelDesinventariarEquipo())); //hacer mas compacto

        opcionListarConsultas.addActionListener(e -> cambiarPanel(new PanelListarConsultas())); 
        opcionAgregarConsultas.addActionListener(e -> cambiarPanel(new PanelAgregarConsulta())); //Acomodar TODO
        opcionEliminarConsultas.addActionListener(e -> cambiarPanel(new PanelEliminarConsulta())); //hacer mas compacto

        opcionReportePacientes.addActionListener(e -> cambiarPanel(new PanelReportePacientes())); 
        opcionReporteMedicos.addActionListener(e -> cambiarPanel(new PanelReporteMedicos()));
        opcionReporteEquipos.addActionListener(e -> cambiarPanel(new PanelReporteEquiposMedicos()));
        opcionReporteConsultas.addActionListener(e -> cambiarPanel(new PanelReporteConsultas())); //acomodar

        opcionSalir.addActionListener(e -> System.exit(0));
    }

    // Método para cambiar el panel central
    private void cambiarPanel(JPanel nuevoPanel) {
        remove(panelCentral);
        nuevoPanel.setOpaque(false);
        panelCentral = nuevoPanel;
        add(panelCentral, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
   private class PanelAgregarPacientes extends JPanel {
    private JTextField txtNombre, txtEdad, txtDireccion;
    private JButton btnAgregar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelAgregarPacientes() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoAgregarPaciente.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(400, 0)); // ancho fijo para la imagen
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(5);
        panelFormulario.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        panelFormulario.add(txtDireccion, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnAgregar = new JButton("Agregar");
        panelFormulario.add(btnAgregar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción del botón
        btnAgregar.addActionListener(e -> {
            try {
                fachada.agregarPaciente(
                        txtNombre.getText(),
                        txtEdad.getText(),
                        txtDireccion.getText()
                );
                JOptionPane.showMessageDialog(this, "Paciente agregado con éxito",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}


    
    
    
     private class PanelActualizarPacientes extends JPanel {
    private JTextField txtId, txtNombre, txtEdad, txtDireccion;
    private JButton btnBuscar, btnActualizar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelActualizarPacientes() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoActualizarPaciente.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(400, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(10);
        panelFormulario.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(5);
        panelFormulario.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        panelFormulario.add(txtDireccion, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel botones = new JPanel(new FlowLayout());
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        botones.add(btnBuscar);
        botones.add(btnActualizar);
        panelFormulario.add(botones, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción Buscar
        btnBuscar.addActionListener(e -> {
            try {
                Paciente p = fachada.obtenerPacientePorId(txtId.getText());
                txtNombre.setText(p.getNombre());
                txtEdad.setText(String.valueOf(p.getEdad()));
                txtDireccion.setText(p.getDireccion());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Actualizar
        btnActualizar.addActionListener(e -> {
            try {
                fachada.actualizarPaciente(
                        txtId.getText(),
                        txtNombre.getText(),
                        txtEdad.getText(),
                        txtDireccion.getText()
                );
                JOptionPane.showMessageDialog(this,
                        "Paciente actualizado con éxito",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
    private class PanelConsultarPacientes extends JPanel {
    private JTextField txtId, txtNombre, txtEdad, txtDireccion;
    private JButton btnBuscar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelConsultarPacientes() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoConsultarPaciente.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(350, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(10);
        panelFormulario.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        txtNombre.setEditable(false);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(5);
        txtEdad.setEditable(false);
        panelFormulario.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        txtDireccion.setEditable(false);
        panelFormulario.add(txtDireccion, gbc);

        // Botón
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnBuscar = new JButton("Buscar");
        panelFormulario.add(btnBuscar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción del botón
        btnBuscar.addActionListener(e -> {
            txtNombre.setText("");
            txtEdad.setText("");
            txtDireccion.setText("");
            try {
                if (!txtId.getText().isBlank()) {
                    Paciente p = fachada.obtenerPacientePorId(txtId.getText());
                    txtNombre.setText(p.getNombre());
                    txtEdad.setText(String.valueOf(p.getEdad()));
                    txtDireccion.setText(p.getDireccion());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
     private class PanelEliminarPacientes extends JPanel {
    private JTextField txtId, txtNombre, txtEdad, txtDireccion;
    private JButton btnBuscar, btnEliminar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelEliminarPacientes() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoEliminarPaciente.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(330, 0)); // ancho fijo para la imagen
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(10);
        panelFormulario.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        txtNombre.setEditable(false);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(5);
        txtEdad.setEditable(false);
        panelFormulario.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        txtDireccion.setEditable(false);
        panelFormulario.add(txtDireccion, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel botones = new JPanel(new FlowLayout());
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");
        botones.add(btnBuscar);
        botones.add(btnEliminar);
        panelFormulario.add(botones, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción Buscar
        btnBuscar.addActionListener(e -> {
            txtNombre.setText("");
            txtEdad.setText("");
            txtDireccion.setText("");
            try {
                if (!txtId.getText().isBlank()) {
                    Paciente p = fachada.obtenerPacientePorId(txtId.getText());
                    txtNombre.setText(p.getNombre());
                    txtEdad.setText(String.valueOf(p.getEdad()));
                    txtDireccion.setText(p.getDireccion());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Eliminar
        btnEliminar.addActionListener(e -> {
            try {
                int opcion = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de que deseas eliminar este paciente?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (opcion == JOptionPane.YES_OPTION) {
                    fachada.eliminarPaciente(txtId.getText());
                    JOptionPane.showMessageDialog(this,
                            "Paciente eliminado con éxito",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
     private class PanelListarPacientes extends JPanel {
        private JTextField txtNombre, txtDireccion, txtEdadDesde, txtEdadHasta;
        private JButton btnBuscar;
        private JTable tabla;
        private JLabel lblResultados;
        private final PersistenciaFachada fachada;

        public PanelListarPacientes() {
            fachada = new PersistenciaFachada();
            setLayout(new BorderLayout(10, 10));

            // Título
            add(new JLabel("Listar Pacientes", JLabel.CENTER), BorderLayout.NORTH);

            // Filtros
            JPanel filtros = new JPanel(new GridLayout(2, 4, 5, 5));
            filtros.add(new JLabel("Nombre:"));
            txtNombre = new JTextField();
            filtros.add(txtNombre);

            filtros.add(new JLabel("Dirección:"));
            txtDireccion = new JTextField();
            filtros.add(txtDireccion);

            filtros.add(new JLabel("Edad desde:"));
            txtEdadDesde = new JTextField();
            filtros.add(txtEdadDesde);

            filtros.add(new JLabel("Edad hasta:"));
            txtEdadHasta = new JTextField();
            filtros.add(txtEdadHasta);

            btnBuscar = new JButton("Buscar");
            filtros.add(btnBuscar);

            add(filtros, BorderLayout.NORTH);

            // Tabla
            tabla = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Edad", "Dirección"}, 0));
            add(new JScrollPane(tabla), BorderLayout.CENTER);

            // Resultados
            lblResultados = new JLabel("Resultados: 0");
            add(lblResultados, BorderLayout.SOUTH);

            // Acción Buscar
            btnBuscar.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                try {
                    String desde = txtEdadDesde.getText().isBlank() ? "0" : txtEdadDesde.getText();
                    String hasta = txtEdadHasta.getText().isBlank() ? "0" : txtEdadHasta.getText();

                    List<Paciente> lista = fachada.listarPacientes(
                            txtNombre.getText(),
                            txtDireccion.getText(),
                            desde,
                            hasta
                    );

                    for (Paciente p : lista) {
                        model.addRow(new Object[]{p.getId(), p.getNombre(), p.getEdad(), p.getDireccion()});
                    }

                    lblResultados.setText("Resultados: " + fachada.contarDatos(lista));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

       
     private class PanelAgregarMedicos extends JPanel {
    private JTextField txtNombre;
    private JComboBox<Especialidad> comboEspecialidades;
    private JButton btnAgregar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelAgregarMedicos() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoAgregarMedico.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(400, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Especialidad:"), gbc);
        gbc.gridx = 1;
        comboEspecialidades = new JComboBox<>();
        panelFormulario.add(comboEspecialidades, gbc);

        // Botón
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnAgregar = new JButton("Agregar");
        panelFormulario.add(btnAgregar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Cargar especialidades
        try {
            List<Especialidad> listaEspecialidades = fachada.listarEspecialidades();
            for (Especialidad e : listaEspecialidades) {
                comboEspecialidades.addItem(e);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Acción del botón
        btnAgregar.addActionListener(e -> {
            try {
                if (txtNombre.getText().isBlank() || comboEspecialidades.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Datos incompletos",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    fachada.agregarMedico(txtNombre.getText(),
                            (Especialidad) comboEspecialidades.getSelectedItem());
                    JOptionPane.showMessageDialog(this, "Médico agregado con éxito",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
    private class PanelConsultarMedicos extends JPanel {
    private JTextField txtId, txtNombre, txtEspecialidad;
    private JButton btnBuscar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelConsultarMedicos() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoConsultarMedico.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(400, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(10);
        panelFormulario.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        txtNombre.setEditable(false);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Especialidad:"), gbc);
        gbc.gridx = 1;
        txtEspecialidad = new JTextField(20);
        txtEspecialidad.setEditable(false);
        panelFormulario.add(txtEspecialidad, gbc);

        // Botón
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnBuscar = new JButton("Buscar");
        panelFormulario.add(btnBuscar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción directa
        btnBuscar.addActionListener(e -> {
            txtNombre.setText("");
            txtEspecialidad.setText("");
            try {
                if (!txtId.getText().isBlank()) {
                    Medico m = fachada.obtenerMedicoPorId(txtId.getText());
                    txtNombre.setText(m.getNombre());
                    txtEspecialidad.setText(m.getEspecialidad().getNombre());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
    
    private class PanelListarEquipo extends JPanel {
        private JTextField txtId, txtNombre, txtCantidad;
        private JButton btnBuscar;
        private JCheckBox chkBuscarPorId;
        private JTable tabla;
        private final PersistenciaFachada fachada;

        public PanelListarEquipo() {
            fachada = new PersistenciaFachada();
            setLayout(new BorderLayout(10, 10));

            // Título
            add(new JLabel("Inventario Equipo Médico", JLabel.CENTER), BorderLayout.NORTH);

            // Filtros
            JPanel filtros = new JPanel(new GridLayout(3, 3, 5, 5));
            filtros.add(new JLabel("ID:"));
            txtId = new JTextField();
            txtId.setEditable(false);
            filtros.add(txtId);

            chkBuscarPorId = new JCheckBox("Buscar por ID");
            filtros.add(chkBuscarPorId);

            filtros.add(new JLabel("Nombre:"));
            txtNombre = new JTextField();
            filtros.add(txtNombre);

            filtros.add(new JLabel("Cantidad:"));
            txtCantidad = new JTextField();
            filtros.add(txtCantidad);

            btnBuscar = new JButton("Buscar");
            filtros.add(btnBuscar);

            add(filtros, BorderLayout.NORTH);

            // Tabla
            tabla = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Cantidad"}, 0));
            add(new JScrollPane(tabla), BorderLayout.CENTER);

            // Acción del checkbox
            chkBuscarPorId.addActionListener(e -> {
                if (!chkBuscarPorId.isSelected()) {
                    txtId.setText("");
                    txtId.setEditable(false);
                    txtCantidad.setEditable(true);
                    txtNombre.setEditable(true);
                } else {
                    txtId.setEditable(true);
                    txtCantidad.setText("");
                    txtNombre.setText("");
                    txtCantidad.setEditable(false);
                    txtNombre.setEditable(false);
                }
            });

            // Acción Buscar
            btnBuscar.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                try {
                    if (chkBuscarPorId.isSelected()) {
                        EquipoMedico equipo = fachada.obtenerEquipoMedicoPorId(txtId.getText());
                        model.addRow(new Object[]{equipo.getId(), equipo.getNombre(), equipo.getCantidad()});
                    } else {
                        String cantidad = "-1";
                        if (!txtCantidad.getText().isBlank()) {
                            cantidad = txtCantidad.getText();
                        }

                        List<EquipoMedico> lista = fachada.listarEquiposMedicos(txtNombre.getText(), cantidad);
                        for (EquipoMedico equipoMedico : lista) {
                            model.addRow(new Object[]{equipoMedico.getId(), equipoMedico.getNombre(), equipoMedico.getCantidad()});
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
    
   private class PanelInventariarEquipo extends JPanel {
    private JTextField txtId, txtNombre, txtCantidad, txtCantidadAñadir;
    private JButton btnBuscar, btnAgregar;
    private JCheckBox chkNuevo;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelInventariarEquipo() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda con ancho fijo de 400 px
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoAgregarEquipoMedico.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(400, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario con GridBagLayout
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID + Buscar
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtId = new JTextField(12);
        txtId.setMinimumSize(new Dimension(120, 25));
        panelFormulario.add(txtId, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        btnBuscar = new JButton("Buscar");
        panelFormulario.add(btnBuscar, gbc);

        // Nombre + Checkbox
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombre = new JTextField(15);
        txtNombre.setEditable(false);
        txtNombre.setMinimumSize(new Dimension(150, 25));
        panelFormulario.add(txtNombre, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        chkNuevo = new JCheckBox("Nuevo equipo");
        panelFormulario.add(chkNuevo, gbc);

        // Cantidad existente
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Cantidad existente:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCantidad = new JTextField("0");
        txtCantidad.setEditable(false);
        txtCantidad.setMinimumSize(new Dimension(80, 25));
        panelFormulario.add(txtCantidad, gbc);

        // Cantidad a agregar + Botón
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Cantidad a agregar:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCantidadAñadir = new JTextField(10);
        txtCantidadAñadir.setMinimumSize(new Dimension(80, 25));
        panelFormulario.add(txtCantidadAñadir, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        btnAgregar = new JButton("Agregar");
        panelFormulario.add(btnAgregar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción Buscar
        btnBuscar.addActionListener(e -> {
            txtNombre.setText("");
            txtCantidad.setText("");
            try {
                if (!txtId.getText().isBlank()) {
                    EquipoMedico equipo = fachada.obtenerEquipoMedicoPorId(txtId.getText());
                    txtNombre.setText(equipo.getNombre());
                    txtCantidad.setText(String.valueOf(equipo.getCantidad()));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Agregar
        btnAgregar.addActionListener(e -> {
            try {
                if (txtNombre.getText().isBlank() || txtCantidad.getText().isBlank() || txtCantidadAñadir.getText().isBlank()) {
                    JOptionPane.showMessageDialog(this, "Datos incompletos",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int opcion;
                    String cantidad = String.valueOf(
                            Integer.parseInt(txtCantidad.getText()) + Integer.parseInt(txtCantidadAñadir.getText())
                    );
                    if (chkNuevo.isSelected()) {
                        opcion = JOptionPane.showConfirmDialog(this,
                                "¿Estás seguro de que deseas agregar este equipo al inventario?",
                                "Confirmar inventariado",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (opcion == JOptionPane.YES_OPTION) {
                            fachada.agregarEquipoMedico(txtNombre.getText(), cantidad);
                            JOptionPane.showMessageDialog(this, "Equipo médico añadido con éxito",
                                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        opcion = JOptionPane.showConfirmDialog(this,
                                "¿Estás seguro de que deseas añadir esa cantidad al inventario?",
                                "Confirmar inventariado",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (opcion == JOptionPane.YES_OPTION) {
                            fachada.actualizarCantidadEquipo(txtId.getText(), cantidad);
                            JOptionPane.showMessageDialog(this, "Equipo médico añadido con éxito",
                                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Checkbox
        chkNuevo.addActionListener(e -> {
            if (!chkNuevo.isSelected()) {
                txtNombre.setText("");
                txtCantidad.setText("0");
                txtNombre.setEditable(false);
            } else {
                txtNombre.setEditable(true);
            }
        });
    }
}
    
    private class PanelDesinventariarEquipo extends JPanel {
    private JTextField txtId, txtNombre, txtCantidad, txtCantidadEliminar;
    private JButton btnBuscar, btnEliminar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelDesinventariarEquipo() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoEliminarEquipoMedico.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(400, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID + Buscar
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtId = new JTextField(12);
        txtId.setMinimumSize(new Dimension(120, 25));
        panelFormulario.add(txtId, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        btnBuscar = new JButton("Buscar");
        panelFormulario.add(btnBuscar, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombre = new JTextField(15);
        txtNombre.setEditable(false);
        txtNombre.setMinimumSize(new Dimension(150, 25));
        panelFormulario.add(txtNombre, gbc);

        // Cantidad existente
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Cantidad existente:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCantidad = new JTextField();
        txtCantidad.setEditable(false);
        txtCantidad.setMinimumSize(new Dimension(80, 25));
        panelFormulario.add(txtCantidad, gbc);

        // Cantidad a eliminar + botón
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Cantidad a eliminar:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCantidadEliminar = new JTextField(10);
        txtCantidadEliminar.setMinimumSize(new Dimension(80, 25));
        panelFormulario.add(txtCantidadEliminar, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        btnEliminar = new JButton("Eliminar");
        panelFormulario.add(btnEliminar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción Buscar
        btnBuscar.addActionListener(e -> {
            txtNombre.setText("");
            txtCantidad.setText("");
            try {
                if (!txtId.getText().isBlank()) {
                    EquipoMedico equipo = fachada.obtenerEquipoMedicoPorId(txtId.getText());
                    txtNombre.setText(equipo.getNombre());
                    txtCantidad.setText(String.valueOf(equipo.getCantidad()));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Eliminar
        btnEliminar.addActionListener(e -> {
            try {
                if (txtNombre.getText().isBlank() || txtCantidad.getText().isBlank() || txtCantidadEliminar.getText().isBlank()) {
                    JOptionPane.showMessageDialog(this, "Datos incompletos",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int cantidadExistente = Integer.parseInt(txtCantidad.getText());
                    int cantidadEliminar = Integer.parseInt(txtCantidadEliminar.getText());

                    if (cantidadEliminar > cantidadExistente) {
                        JOptionPane.showMessageDialog(this,
                                "Se desea eliminar una mayor cantidad de lo que hay registrado",
                                "Error", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int opcion = JOptionPane.showConfirmDialog(this,
                                "¿Estás seguro de que deseas eliminar esa cantidad del inventario?",
                                "Confirmar desinventariado",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (opcion == JOptionPane.YES_OPTION) {
                            String nuevaCantidad = String.valueOf(cantidadExistente - cantidadEliminar);
                            fachada.actualizarCantidadEquipo(txtId.getText(), nuevaCantidad);
                            JOptionPane.showMessageDialog(this, "Equipo médico eliminado con éxito",
                                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
    
    private class PanelListarConsultas extends JPanel {
        private JTextField txtIdPaciente, txtIdMedico, txtFechaDesde, txtFechaHasta;
        private JButton btnBuscar;
        private JTable tabla;
        private final PersistenciaFachada fachada;

        public PanelListarConsultas() {
            fachada = new PersistenciaFachada();
            setLayout(new BorderLayout(10, 10));

            // Título
            add(new JLabel("Listar Consultas", JLabel.CENTER), BorderLayout.NORTH);

            // Filtros
            JPanel filtros = new JPanel(new GridLayout(3, 4, 5, 5));
            filtros.add(new JLabel("Id Paciente:"));
            txtIdPaciente = new JTextField();
            filtros.add(txtIdPaciente);

            filtros.add(new JLabel("Id Médico:"));
            txtIdMedico = new JTextField();
            filtros.add(txtIdMedico);

            filtros.add(new JLabel("Fecha inicio (dd/mm/aaaa):"));
            txtFechaDesde = new JTextField();
            filtros.add(txtFechaDesde);

            filtros.add(new JLabel("Fecha fin (dd/mm/aaaa):"));
            txtFechaHasta = new JTextField();
            filtros.add(txtFechaHasta);

            btnBuscar = new JButton("Buscar");
            filtros.add(btnBuscar);

            add(filtros, BorderLayout.NORTH);

            // Tabla
            tabla = new JTable(new DefaultTableModel(
                    new Object[]{"ID", "Id Paciente", "Nombre Paciente", "Edad Paciente", "Dirección Paciente",
                            "Id Médico", "Nombre Médico", "Especialidad Médico", "Fecha"}, 0));
            add(new JScrollPane(tabla), BorderLayout.CENTER);

            // Acción Buscar
            btnBuscar.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                try {
                    String f1 = "01/01/0001";
                    String f2 = "31/12/9999";
                    Paciente paciente = null;
                    Medico medico = null;

                    if (!txtIdPaciente.getText().isBlank()) {
                        paciente = fachada.obtenerPacientePorId(txtIdPaciente.getText());
                    }
                    if (!txtIdMedico.getText().isBlank()) {
                        medico = fachada.obtenerMedicoPorId(txtIdMedico.getText());
                    }
                    if (!txtFechaDesde.getText().isBlank()) {
                        f1 = txtFechaDesde.getText();
                    }
                    if (!txtFechaHasta.getText().isBlank()) {
                        f2 = txtFechaHasta.getText();
                    }

                    List<Consulta> lista = fachada.listarConsultas(paciente, medico, f1, f2);
                    for (Consulta consulta : lista) {
                        model.addRow(new Object[]{
                                consulta.getId(),
                                consulta.getPaciente().getId(),
                                consulta.getPaciente().getNombre(),
                                consulta.getPaciente().getEdad(),
                                consulta.getPaciente().getDireccion(),
                                consulta.getMedico().getId(),
                                consulta.getMedico().getNombre(),
                                consulta.getMedico().getEspecialidad().getNombre(),
                                consulta.getFecha().toString()
                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
    
  private class PanelAgregarConsulta extends JPanel {
    private JTextField txtIdPaciente, txtNombrePaciente, txtEdadPaciente, txtDireccionPaciente;
    private JButton btnBuscarPaciente;
    private JTextField txtIdMedico, txtNombreMedico;
    private JLabel labelEspecialidad;
    private JButton btnBuscarMedico;
    private JTextField txtFecha;
    private JButton btnAgregar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelAgregarConsulta() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoAgendarConsultaMedica.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(300, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID Paciente + Buscar
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelFormulario.add(new JLabel("ID Paciente:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtIdPaciente = new JTextField(12);
        txtIdPaciente.setMinimumSize(new Dimension(120, 25));
        panelFormulario.add(txtIdPaciente, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        btnBuscarPaciente = new JButton("Buscar");
        panelFormulario.add(btnBuscarPaciente, gbc);

        // Nombre Paciente
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombrePaciente = new JTextField(15);
        txtNombrePaciente.setEditable(false);
        panelFormulario.add(txtNombrePaciente, gbc);

        // Edad Paciente
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 3;
        txtEdadPaciente = new JTextField(5);
        txtEdadPaciente.setEditable(false);
        panelFormulario.add(txtEdadPaciente, gbc);

        // Dirección Paciente
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtDireccionPaciente = new JTextField(20);
        txtDireccionPaciente.setEditable(false);
        panelFormulario.add(txtDireccionPaciente, gbc);
        gbc.gridwidth = 1;

        // ID Médico + Buscar
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("ID Médico:"), gbc);
        gbc.gridx = 1;
        txtIdMedico = new JTextField(12);
        panelFormulario.add(txtIdMedico, gbc);
        gbc.gridx = 2;
        btnBuscarMedico = new JButton("Buscar");
        panelFormulario.add(btnBuscarMedico, gbc);

        // Nombre Médico
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombreMedico = new JTextField(15);
        txtNombreMedico.setEditable(false);
        panelFormulario.add(txtNombreMedico, gbc);

        // Especialidad Médico con Spinner
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Especialidad:"), gbc);
        gbc.gridx = 3;

            labelEspecialidad = new JLabel("especialidad");
            labelEspecialidad.setEnabled(false);

        panelFormulario.add(labelEspecialidad, gbc);

        // Fecha + Agregar
        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Fecha (dd/mm/aaaa):"), gbc);
        gbc.gridx = 1;
        txtFecha = new JTextField(10);
        panelFormulario.add(txtFecha, gbc);
        gbc.gridx = 2;
        btnAgregar = new JButton("Agregar");
        panelFormulario.add(btnAgregar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción Buscar Paciente
        btnBuscarPaciente.addActionListener(e -> {
            txtNombrePaciente.setText("");
            txtEdadPaciente.setText("");
            txtDireccionPaciente.setText("");
            try {
                if (!txtIdPaciente.getText().isBlank()) {
                    Paciente p = fachada.obtenerPacientePorId(txtIdPaciente.getText());
                    txtNombrePaciente.setText(p.getNombre());
                    txtEdadPaciente.setText(String.valueOf(p.getEdad()));
                    txtDireccionPaciente.setText(p.getDireccion());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Buscar Médico
        btnBuscarMedico.addActionListener(e -> {
            txtNombreMedico.setText("");
            labelEspecialidad.setEnabled(false);
            try {
                if (!txtIdMedico.getText().isBlank()) {
                    Medico m = fachada.obtenerMedicoPorId(txtIdMedico.getText());
                    txtNombreMedico.setText(m.getNombre());
                    labelEspecialidad.setText(m.getEspecialidad().getNombre());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Agregar Consulta
        btnAgregar.addActionListener(e -> {
            if (txtIdPaciente.getText().isBlank() || txtIdMedico.getText().isBlank() || txtFecha.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Datos incompletos",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    int opcion = JOptionPane.showConfirmDialog(this,
                            "¿Estás seguro de que deseas crear esta consulta?",
                            "Confirmar creación de consulta",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (opcion == JOptionPane.YES_OPTION) {
                        Paciente p = fachada.obtenerPacientePorId(txtIdPaciente.getText());
                        Medico m = fachada.obtenerMedicoPorId(txtIdMedico.getText());

                        fachada.agregarConsulta(p, m, txtFecha.getText());

                        JOptionPane.showMessageDialog(this,
                                "Consulta del paciente: " + p.toString() +
                                " con el médico: " + m.toString() +
                                " el día: " + txtFecha.getText() +
                                " fue registrada con éxito",
                                "Consulta registrada", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
    
    private class PanelEliminarConsulta extends JPanel {
    private JTextField txtId, txtNombrePaciente, txtNombreMedico, txtFecha;
    private JButton btnBuscar, btnEliminar;
    private final PersistenciaFachada fachada;
    private Image backgroundImage;

    public PanelEliminarConsulta() {
        fachada = new PersistenciaFachada();
        setLayout(new BorderLayout());

        // Panel de imagen a la izquierda
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("imagenes/fondoEliminarConsultaMedica.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }
        panelImagen.setPreferredSize(new Dimension(400, 0));
        add(panelImagen, BorderLayout.WEST);

        // Panel de formulario alineado a la izquierda
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID + Buscar
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtId = new JTextField(12);
        txtId.setMinimumSize(new Dimension(120, 25));
        panelFormulario.add(txtId, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        btnBuscar = new JButton("Buscar");
        panelFormulario.add(btnBuscar, gbc);

        // Paciente
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombrePaciente = new JTextField(15);
        txtNombrePaciente.setEditable(false);
        txtNombrePaciente.setMinimumSize(new Dimension(150, 25));
        panelFormulario.add(txtNombrePaciente, gbc);

        // Médico
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Médico:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombreMedico = new JTextField(15);
        txtNombreMedico.setEditable(false);
        txtNombreMedico.setMinimumSize(new Dimension(150, 25));
        panelFormulario.add(txtNombreMedico, gbc);

        // Fecha + Eliminar
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Fecha:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtFecha = new JTextField(15);
        txtFecha.setEditable(false);
        txtFecha.setMinimumSize(new Dimension(120, 25));
        panelFormulario.add(txtFecha, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        btnEliminar = new JButton("Eliminar");
        panelFormulario.add(btnEliminar, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Acción Buscar
        btnBuscar.addActionListener(e -> {
            txtNombrePaciente.setText("");
            txtNombreMedico.setText("");
            txtFecha.setText("");
            try {
                if (!txtId.getText().isBlank()) {
                    Consulta c = fachada.obtenerConsultaPorId(txtId.getText());
                    txtNombrePaciente.setText(c.getPaciente().getNombre());
                    txtNombreMedico.setText(c.getMedico().getNombre());
                    txtFecha.setText(c.getFecha().toString());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Eliminar
        btnEliminar.addActionListener(e -> {
            try {
                int opcion = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de que deseas eliminar esta consulta?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (opcion == JOptionPane.YES_OPTION) {
                    fachada.eliminarConsulta(txtId.getText());
                    JOptionPane.showMessageDialog(this,
                            "Consulta eliminada con éxito",
                            "Consulta eliminada", JOptionPane.INFORMATION_MESSAGE);

                    txtId.setText("");
                    txtNombrePaciente.setText("");
                    txtNombreMedico.setText("");
                    txtFecha.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
    
    private class PanelReportePacientes extends JPanel {
        private JTextField txtNombre, txtDireccion, txtEdadDesde, txtEdadHasta;
        private JButton btnBuscar, btnGenerarReporte;
        private JTable tabla;
        private JTextArea txtReporte;
        private JLabel lblResultados;
        private JCheckBox chkMayusculas;
        private final PersistenciaFachada fachada;

        public PanelReportePacientes() {
            fachada = new PersistenciaFachada();
            setLayout(new BorderLayout(10, 10));

            // Título
            add(new JLabel("Reporte de Pacientes", JLabel.CENTER), BorderLayout.NORTH);

            // Filtros
            JPanel filtros = new JPanel(new GridLayout(3, 4, 5, 5));
            filtros.add(new JLabel("Nombre:"));
            txtNombre = new JTextField();
            filtros.add(txtNombre);

            filtros.add(new JLabel("Dirección:"));
            txtDireccion = new JTextField();
            filtros.add(txtDireccion);

            filtros.add(new JLabel("Edad desde:"));
            txtEdadDesde = new JTextField();
            filtros.add(txtEdadDesde);

            filtros.add(new JLabel("Edad hasta:"));
            txtEdadHasta = new JTextField();
            filtros.add(txtEdadHasta);

            chkMayusculas = new JCheckBox("Nombres en mayúsculas");
            filtros.add(chkMayusculas);

            btnBuscar = new JButton("Buscar");
            filtros.add(btnBuscar);

            add(filtros, BorderLayout.NORTH);

            // Tabla
            tabla = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Edad", "Dirección"}, 0));
            add(new JScrollPane(tabla), BorderLayout.CENTER);

            // Resultados + Reporte
            JPanel reportePanel = new JPanel(new BorderLayout(5, 5));
            lblResultados = new JLabel("Resultados: 0");
            reportePanel.add(lblResultados, BorderLayout.NORTH);

            btnGenerarReporte = new JButton("Generar reporte");
            reportePanel.add(btnGenerarReporte, BorderLayout.CENTER);

            txtReporte = new JTextArea(5, 20);
            txtReporte.setEditable(false);
            reportePanel.add(new JScrollPane(txtReporte), BorderLayout.SOUTH);

            add(reportePanel, BorderLayout.SOUTH);

            // Acción Buscar (equivalente a botonBuscarActionPerformed)
            btnBuscar.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                try {
                    String desde = txtEdadDesde.getText().isBlank() ? "0" : txtEdadDesde.getText();
                    String hasta = txtEdadHasta.getText().isBlank() ? "0" : txtEdadHasta.getText();

                    List<Paciente> lista = fachada.listarPacientes(
                            txtNombre.getText(),
                            txtDireccion.getText(),
                            desde,
                            hasta
                    );

                    if (chkMayusculas.isSelected()) {
                        lista = fachada.listaNombresPacienteMayusculas(lista);
                    }

                    for (Paciente p : lista) {
                        model.addRow(new Object[]{p.getId(), p.getNombre(), p.getEdad(), p.getDireccion()});
                    }

                    lblResultados.setText("Resultados: " + fachada.contarDatos(lista));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Acción Generar Reporte (equivalente a botonGenerarReporteActionPerformed)
            btnGenerarReporte.addActionListener(e -> {
                try {
                    String desde = txtEdadDesde.getText().isBlank() ? "0" : txtEdadDesde.getText();
                    String hasta = txtEdadHasta.getText().isBlank() ? "0" : txtEdadHasta.getText();

                    List<Paciente> lista = fachada.listarPacientes(
                            txtNombre.getText(),
                            txtDireccion.getText(),
                            desde,
                            hasta
                    );

                    if (chkMayusculas.isSelected()) {
                        lista = fachada.listaNombresPacienteMayusculas(lista);
                    }

                    txtReporte.setText(fachada.generarReportePaciente(lista));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
    
    private class PanelReporteMedicos extends JPanel {
        private JTextField txtNombre, txtEspecialidad;
        private JButton btnBuscar, btnGenerarReporte;
        private JTable tabla;
        private JTextArea txtReporte;
        private JLabel lblResultados;
        private JCheckBox chkMayusculas, chkEspecialidades;
        private final PersistenciaFachada fachada;

        public PanelReporteMedicos() {
            fachada = new PersistenciaFachada();
            setLayout(new BorderLayout(10, 10));

            // Título
            add(new JLabel("Reporte de Médicos", JLabel.CENTER), BorderLayout.NORTH);

            // Filtros
            JPanel filtros = new JPanel(new GridLayout(3, 2, 5, 5));
            filtros.add(new JLabel("Nombre:"));
            txtNombre = new JTextField();
            filtros.add(txtNombre);

            filtros.add(new JLabel("Especialidad:"));
            txtEspecialidad = new JTextField();
            filtros.add(txtEspecialidad);

            chkEspecialidades = new JCheckBox("Ordenar por especialidades");
            filtros.add(chkEspecialidades);

            chkMayusculas = new JCheckBox("Nombres en mayúsculas");
            filtros.add(chkMayusculas);

            btnBuscar = new JButton("Buscar");
            filtros.add(btnBuscar);

            add(filtros, BorderLayout.NORTH);

            // Tabla
            tabla = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Especialidad"}, 0));
            add(new JScrollPane(tabla), BorderLayout.CENTER);

            // Resultados + Reporte
            JPanel reportePanel = new JPanel(new BorderLayout(5, 5));
            lblResultados = new JLabel("Resultados: 0");
            reportePanel.add(lblResultados, BorderLayout.NORTH);

            btnGenerarReporte = new JButton("Generar reporte");
            reportePanel.add(btnGenerarReporte, BorderLayout.CENTER);

            txtReporte = new JTextArea(5, 20);
            txtReporte.setEditable(false);
            reportePanel.add(new JScrollPane(txtReporte), BorderLayout.SOUTH);

            add(reportePanel, BorderLayout.SOUTH);

            // Acción Buscar (equivalente a botonBuscarActionPerformed)
            btnBuscar.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                try {
                    List<Medico> lista = fachada.listarMedicos(txtNombre.getText(), txtEspecialidad.getText());

                    if (chkMayusculas.isSelected()) {
                        lista = fachada.listaNombresMedicoMayusculas(lista);
                    }
                    if (chkEspecialidades.isSelected()) {
                        lista = fachada.listaOrdenarEspecialidades(lista);
                    }

                    for (Medico m : lista) {
                        model.addRow(new Object[]{m.getId(), m.getNombre(), m.getEspecialidad().getNombre()});
                    }

                    lblResultados.setText("Resultados: " + fachada.contarDatos(lista));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Acción Generar Reporte (equivalente a botonGenerarReporteActionPerformed)
            btnGenerarReporte.addActionListener(e -> {
                try {
                    List<Medico> lista = fachada.listarMedicos(txtNombre.getText(), txtEspecialidad.getText());

                    if (chkMayusculas.isSelected()) {
                        lista = fachada.listaNombresMedicoMayusculas(lista);
                    }
                    if (chkEspecialidades.isSelected()) {
                        lista = fachada.listaOrdenarEspecialidades(lista);
                    }

                    txtReporte.setText(fachada.generarReporteMedico(lista));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
    
    private class PanelReporteEquiposMedicos extends JPanel {
        private JTextField txtNombre, txtCantidad;
        private JButton btnBuscar, btnGenerarReporte;
        private JTable tabla;
        private JTextArea txtReporte;
        private JLabel lblCantidadTotal;
        private JCheckBox chkMenor;
        private final PersistenciaFachada fachada;

        public PanelReporteEquiposMedicos() {
            fachada = new PersistenciaFachada();
            setLayout(new BorderLayout(10, 10));

            // Título
            add(new JLabel("Reporte de Equipos Médicos", JLabel.CENTER), BorderLayout.NORTH);

            // Filtros
            JPanel filtros = new JPanel(new GridLayout(3, 2, 5, 5));
            filtros.add(new JLabel("Nombre:"));
            txtNombre = new JTextField();
            filtros.add(txtNombre);

            filtros.add(new JLabel("Cantidad:"));
            txtCantidad = new JTextField();
            filtros.add(txtCantidad);

            chkMenor = new JCheckBox("Cantidad menor");
            filtros.add(chkMenor);

            btnBuscar = new JButton("Buscar");
            filtros.add(btnBuscar);

            add(filtros, BorderLayout.NORTH);

            // Tabla
            tabla = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Cantidad"}, 0));
            add(new JScrollPane(tabla), BorderLayout.CENTER);

            // Resultados + Reporte
            JPanel reportePanel = new JPanel(new BorderLayout(5, 5));
            lblCantidadTotal = new JLabel("Cantidad total: 0");
            reportePanel.add(lblCantidadTotal, BorderLayout.NORTH);

            btnGenerarReporte = new JButton("Generar reporte");
            reportePanel.add(btnGenerarReporte, BorderLayout.CENTER);

            txtReporte = new JTextArea(5, 20);
            txtReporte.setEditable(false);
            reportePanel.add(new JScrollPane(txtReporte), BorderLayout.SOUTH);

            add(reportePanel, BorderLayout.SOUTH);

            // Acción Buscar (equivalente a botonBuscarActionPerformed)
            btnBuscar.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                try {
                    List<EquipoMedico> lista;
                    String cantidad = "-1";
                    if (!txtCantidad.getText().isBlank()) {
                        cantidad = txtCantidad.getText();
                    }

                    if (chkMenor.isSelected()) {
                        lista = fachada.listarEquiposMedicosBajos(txtNombre.getText(), cantidad);
                        lista = fachada.listaOrdenarCantidadInv(lista);
                    } else {
                        lista = fachada.listarEquiposMedicos(txtNombre.getText(), cantidad);
                    }

                    for (EquipoMedico equipo : lista) {
                        model.addRow(new Object[]{equipo.getId(), equipo.getNombre(), equipo.getCantidad()});
                    }

                    lblCantidadTotal.setText("Cantidad total: " + fachada.sumatoriaCantidades(lista));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Acción Generar Reporte (equivalente a botonGenerarReporteActionPerformed)
            btnGenerarReporte.addActionListener(e -> {
                try {
                    List<EquipoMedico> lista;
                    String cantidad = "-1";
                    if (!txtCantidad.getText().isBlank()) {
                        cantidad = txtCantidad.getText();
                    }

                    if (chkMenor.isSelected()) {
                        lista = fachada.listarEquiposMedicosBajos(txtNombre.getText(), cantidad);
                        lista = fachada.listaOrdenarCantidadInv(lista);
                    } else {
                        lista = fachada.listarEquiposMedicos(txtNombre.getText(), cantidad);
                    }

                    txtReporte.setText(fachada.generarReporteEquipoMedico(lista));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
    
    private class PanelReporteConsultas extends JPanel {
        private JTextField txtIdPaciente, txtIdMedico, txtFechaDesde, txtFechaHasta;
        private JButton btnBuscar, btnGenerarReporte;
        private JTable tabla;
        private JTextArea txtReporte;
        private final PersistenciaFachada fachada;

        public PanelReporteConsultas() {
            fachada = new PersistenciaFachada();
            setLayout(new BorderLayout(10, 10));

            // Título
            add(new JLabel("Reportes Consultas", JLabel.CENTER), BorderLayout.NORTH);

            // Filtros
            JPanel filtros = new JPanel(new GridLayout(3, 4, 5, 5));
            filtros.add(new JLabel("Id Paciente:"));
            txtIdPaciente = new JTextField();
            filtros.add(txtIdPaciente);

            filtros.add(new JLabel("Id Médico:"));
            txtIdMedico = new JTextField();
            filtros.add(txtIdMedico);

            filtros.add(new JLabel("Fecha inicio (dd/mm/aaaa):"));
            txtFechaDesde = new JTextField();
            filtros.add(txtFechaDesde);

            filtros.add(new JLabel("Fecha fin (dd/mm/aaaa):"));
            txtFechaHasta = new JTextField();
            filtros.add(txtFechaHasta);

            btnBuscar = new JButton("Buscar");
            filtros.add(btnBuscar);

            add(filtros, BorderLayout.NORTH);

            // Tabla
            tabla = new JTable(new DefaultTableModel(
                    new Object[]{"ID", "Id Paciente", "Nombre Paciente", "Edad Paciente", "Dirección Paciente",
                            "Id Médico", "Nombre Médico", "Especialidad Médico", "Fecha"}, 0));
            add(new JScrollPane(tabla), BorderLayout.CENTER);

            // Reporte
            JPanel reportePanel = new JPanel(new BorderLayout(5, 5));
            btnGenerarReporte = new JButton("Generar reporte");
            reportePanel.add(btnGenerarReporte, BorderLayout.NORTH);

            txtReporte = new JTextArea(5, 20);
            txtReporte.setEditable(false);
            reportePanel.add(new JScrollPane(txtReporte), BorderLayout.CENTER);

            add(reportePanel, BorderLayout.SOUTH);

            // Acción Buscar
            btnBuscar.addActionListener(e -> {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                try {
                    String f1 = "01/01/0001";
                    String f2 = "31/12/9999";
                    Paciente paciente = null;
                    Medico medico = null;

                    if (!txtIdPaciente.getText().isBlank()) {
                        paciente = fachada.obtenerPacientePorId(txtIdPaciente.getText());
                    }
                    if (!txtIdMedico.getText().isBlank()) {
                        medico = fachada.obtenerMedicoPorId(txtIdMedico.getText());
                    }
                    if (!txtFechaDesde.getText().isBlank()) {
                        f1 = txtFechaDesde.getText();
                    }
                    if (!txtFechaHasta.getText().isBlank()) {
                        f2 = txtFechaHasta.getText();
                    }

                    List<Consulta> lista = fachada.listarConsultas(paciente, medico, f1, f2);
                    for (Consulta c : lista) {
                        model.addRow(new Object[]{
                                c.getId(),
                                c.getPaciente().getId(),
                                c.getPaciente().getNombre(),
                                c.getPaciente().getEdad(),
                                c.getPaciente().getDireccion(),
                                c.getMedico().getId(),
                                c.getMedico().getNombre(),
                                c.getMedico().getEspecialidad().getNombre(),
                                c.getFecha().toString()
                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Acción Generar Reporte
            btnGenerarReporte.addActionListener(e -> {
                try {
                    String f1 = "01/01/0001";
                    String f2 = "31/12/9999";
                    Paciente paciente = null;
                    Medico medico = null;

                    if (!txtIdPaciente.getText().isBlank()) {
                        paciente = fachada.obtenerPacientePorId(txtIdPaciente.getText());
                    }
                    if (!txtIdMedico.getText().isBlank()) {
                        medico = fachada.obtenerMedicoPorId(txtIdMedico.getText());
                    }
                    if (!txtFechaDesde.getText().isBlank()) {
                        f1 = txtFechaDesde.getText();
                    }
                    if (!txtFechaHasta.getText().isBlank()) {
                        f2 = txtFechaHasta.getText();
                    }

                    List<Consulta> lista = fachada.listarConsultas(paciente, medico, f1, f2);
                    txtReporte.setText(fachada.generarReporteConsultas(lista)); // ✅ corregido
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
     
}