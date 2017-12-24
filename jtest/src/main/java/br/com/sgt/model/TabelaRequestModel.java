package br.com.sgt.model;

import br.com.sgt.entidades.Request;
import com.google.common.collect.Lists;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Felipe de Brito Lira
 */
public class TabelaRequestModel extends AbstractTableModel{

    private final String[] columns; 
    private final List<Request> requests;
    private Request request;
    
    public TabelaRequestModel(){
        columns = new String[]{"Selecionado", "Método", "Path", };
        requests = Lists.newArrayList();
    }
    
    public void add(List<Request> requests){
        this.requests.addAll(requests);
    }
    
    @Override
    public int getRowCount() {
        return requests.size();
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
        this.requests.get(rowIndex).setSelecionado((Boolean)aValue);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        this.request = this.requests.get(rowIndex);
        
        switch(columnIndex){
            case 0: return this.request.getSelecionado();
            case 1: return this.request.getMethod();
            case 2: return this.request.getPath();
            default: return "Não identificado";
        }
        
    }
    
}
