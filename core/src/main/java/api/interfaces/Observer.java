package api.interfaces;

import api.Advert;
import api.AdvertEvent;

import java.util.List;

public interface Observer {
    /**
     * Наблюдатель за измененными объявлениями
     * 
     * @param ad Модифицированное объявление 
     * @param type Тип изменения, 1 - Новое, 2 - Обновление существующего
     */
    void update(List<Advert> ad, AdvertEvent type);

}
