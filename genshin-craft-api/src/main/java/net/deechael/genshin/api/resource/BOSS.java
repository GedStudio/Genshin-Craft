package net.deechael.genshin.api.resource;

public interface BOSS extends RevivableResource {

    @Override
    default int getRevivalSeconds() {
        return 5 * 60; // 5 minutes
    }

}
