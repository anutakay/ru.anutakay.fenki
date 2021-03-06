package ru.anutakay.fenki.view;

public abstract class FigureFactory<T extends Iterator2D> {

    Adapter<T, ? super Object> mAdapter;

    FigureFactory(Adapter<T, ? super Object> adapter) {
        setAdapter(adapter);
    }

    private void setAdapter(Adapter<T, ? super Object> adapter) {
        mAdapter = adapter;
    }

    public Adapter<? super T, ? super Object> getAdapter() {
        return mAdapter;
    }

    public T getIterator() {
        return mAdapter.getIterator();
    }

    public abstract Figure makeFigure(T it);

}
