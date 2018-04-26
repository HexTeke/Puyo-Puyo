package puyopuyo;

public class PuyoGroup {
    
    private Puyo[] group;
    private char face;
    
    public PuyoGroup() {
        group = new Puyo[2];
        face = 'n';
    }
    
    public void rotateClockWise() {
        switch(face) {
            case 'n':
                group[0].moveX(1);
                group[0].moveY(-1);
                face = 'e';
                break;
            case 'e':
                group[0].moveX(-1);
                group[0].moveY(-1);
                face = 's';
                break;
            case 's':
                group[0].moveX(-1);
                group[0].moveY(1);
                face = 'w';
                break;
            case 'w':
                group[0].moveX(1);
                group[0].moveY(1);
                face = 'n';
                break;
        }
    }

    public void rotateCounter() {
        switch(face) {
            case 'n':
                group[0].moveX(-1);
                group[0].moveY(-1);
                face = 'w';
                break;
            case 'w':
                group[0].moveX(1);
                group[0].moveY(-1);
                face = 's';
                break;
            case 's':
                group[0].moveX(1);
                group[0].moveY(1);
                face = 'e';
                break;
            case 'e':
                group[0].moveX(-1);
                group[0].moveY(1);
                face = 'n';
                break;
        }
    }
}
