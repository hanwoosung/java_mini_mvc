package sports.abstracts_;

import interfaces_.MyLogger;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AbstractSportData<T> implements  Iterable<T> {
    private ArrayList<T> teams;

    public MyLogger logger=null;

    public AbstractSportData(int size) {
        teams = new ArrayList<>(size);
    }

    public AbstractSportData setLogger(MyLogger logger){
        this.logger=logger;
        return  this;
    }

    public AbstractSportData add(T team) {
        teams.add(team);
        if(logger!=null){
            logger.log(getDataName(team) + " 팀 등록");
        }
//        System.out.println(team.getTeamName() + " 팀 등록");
        return this;
    }

    protected String getDataName(T team) {
        return team.toString();
    }

    public void print(StringBuilder sb) {
        print(sb,"\r\n");
    }

    public void print(StringBuilder sb,String sep) {
        for (int i = 0; i < teams.size(); i++) {
            if(sb.length()>0){
                sb.append(sep);
            }
            sb.append((i + 1) + " 팀 이름: " + getDataName(teams.get(i)));
        }
    }

    public void print(MyLogger logger) {
        StringBuilder sb=new StringBuilder();
        print(sb);
        logger.log(sb.toString());
    }

    public T get(int index) {
        if (index >= 0 && index < teams.size()) {
            return teams.get(index);
        }
        return null;
    }

    public int getCount() {
        return teams.size();
    }

    public boolean isEmpty() {
        return teams.isEmpty();
    }

    public void clear() {
        for (T t :teams){
            if(t instanceof Closeable){
                try {
                    ((Closeable)t).close();
                } catch (IOException ignore) {
                }
            }
        }
        teams.clear();
    }

    public ArrayList<T> getAll() {
        return new ArrayList<>(teams);
    }

    @Override
    public Iterator<T> iterator() {
        return teams.iterator();
    }
}
