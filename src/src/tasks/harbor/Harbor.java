package src.tasks.harbor;

class Harbor {

    private WhoreHouse whoreHouse;

    private static class HarborSingleton{
        private final static Harbor HARBOR_INSTANCE = new Harbor();
    }

    public static Harbor getInstance(WhoreHouse whoreHouse){
        Harbor harbor = HarborSingleton.HARBOR_INSTANCE;
        harbor.whoreHouse = whoreHouse;
        return harbor;
    }

    public synchronized WhoreHouse getWhoreHouse() {
        return whoreHouse;
    }

}
