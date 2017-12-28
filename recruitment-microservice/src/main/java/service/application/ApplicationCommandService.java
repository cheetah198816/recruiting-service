package service.application;

import events.application.BaseApplicationEvent;

/**
 * Created by chetan on 28.12.2017.
 */
public interface ApplicationCommandService {

    /**
     * Saves the application event in the event store.
     * @param baseApplicationEvent application event.
     * @return application event.
     */
    BaseApplicationEvent sendApplicationStatusEvent(BaseApplicationEvent baseApplicationEvent);
}
