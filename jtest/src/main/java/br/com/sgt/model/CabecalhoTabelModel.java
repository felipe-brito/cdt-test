package br.com.sgt.model;

import br.com.sgt.entidades.Cabecalho;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Felipe de Brito Lira
 */
public class CabecalhoTabelModel extends AbstractTableModel {

    private final String[] columns;
    private final Set<Cabecalho> headers;
    private Cabecalho head;

    public CabecalhoTabelModel() {
        columns = new String[]{"Selecionado", "Header"};
        headers = Sets.newHashSet();
    }

    public void clean() {
        if (this.headers != null && !this.headers.isEmpty()) {
            this.headers.clear();
        }
    }

    public void add(Set<Cabecalho> headers) {
        this.headers.addAll(headers);
    }

    public List<Cabecalho> getSelecionados() {

        return this.headers.stream().filter(h -> h.getSelecionado()).collect(Collectors.toList());
    }

    @Override
    public int getRowCount() {
        return headers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ((Cabecalho) this.headers.toArray()[rowIndex]).setSelecionado((Boolean) aValue);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        this.head = ((Cabecalho) this.headers.toArray()[rowIndex]);

        switch (columnIndex) {
            case 0:
                return this.head.getSelecionado();
            case 1:
                return this.head.getDescricao();
            default:
                return "Não identificado";
        }

    }

}
