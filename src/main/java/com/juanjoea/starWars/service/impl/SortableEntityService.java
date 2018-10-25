package com.juanjoea.starWars.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.juanjoea.starWars.dto.SortableDto;

public abstract class SortableEntityService implements com.juanjoea.starWars.service.SortableEntityService {

    @Override
    public List<? extends SortableDto> sort(List<? extends SortableDto> list, String field, String order) {
        final int orderModifier;
        if("false".equals(order)) {
            orderModifier = -1;
        } else {
            orderModifier = 1;
        }
        Comparator<SortableDto> comparator = new Comparator<SortableDto>() {
            @Override
            public int compare(SortableDto left, SortableDto right) {
                try {
                    final String leftValue = BeanUtils.getProperty(left, field);
                    final String rightValue = BeanUtils.getProperty(right, field);
                    
                    if("created".equals(field)) {
                    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
                    	final Date leftDate = dateFormat.parse(leftValue);
                    	final Date rightDate = dateFormat.parse(rightValue);
                    	
                        return orderModifier * 
                            leftDate.compareTo(rightDate);
                    } else {
                        return orderModifier * leftValue.compareTo(rightValue);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                	e.printStackTrace();
                    return 0;
                } catch (ParseException e) {					
					e.printStackTrace();
					return 0;
				}
            }
        };

        Collections.sort(list, comparator); 
        return list;
    }

}
