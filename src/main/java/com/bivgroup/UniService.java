package com.bivgroup;

import com.bivgroup.Uni;

public interface UniService {

    public Response addUni(Uni uni);

    public Response deleteUni(int id);

    public Uni getUniId(int id);

    public Uni[] getAllUnies();

}
