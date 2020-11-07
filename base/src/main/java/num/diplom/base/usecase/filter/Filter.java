package num.diplom.base.usecase.filter;

public class Filter {
    private DateFilter dateFilter;
    private TableFilter tableFilter;

    public Filter() {
    }

    public Filter(DateFilter dateFilter, TableFilter tableFilter) {
        this.dateFilter = dateFilter;
        this.tableFilter = tableFilter;
    }

    public DateFilter getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(DateFilter dateFilter) {
        this.dateFilter = dateFilter;
    }

    public TableFilter getTableFilter() {
        return tableFilter;
    }

    public void setTableFilter(TableFilter tableFilter) {
        this.tableFilter = tableFilter;
    }
}
