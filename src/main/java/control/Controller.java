package control;

import domain.smartcitizen.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {


    @Autowired
    SantanderAPIService santanderAPIService;

    @Autowired
    SmartphoneAPIService smartphoneAPIService;

    @Autowired
    PatraAPIService patraAPIService;

    @Autowired
    LondonAPIService londonAPIService;

    @Autowired
    ModelService modelService;

    @RequestMapping("api/v1/entities/{uuid}/readings")
    public Response getData(@PathVariable(value = "uuid") String uuid, @RequestParam(value = "attribute_id") String attribute_id,
                            @RequestParam(value = "from") String start, @RequestParam(value = "to") String end,
                            @RequestParam(value = "function", required = false) String function,
                            @RequestParam(value = "rollup", required = false) String rollup, //m, h , d
                            @RequestParam(value = "limit", required = false) String limit,
                            @RequestParam(value = "offset", required = false) String offset,
                            HttpServletResponse response) throws Exception {
        Response r = null;
        try {
            if (uuid.startsWith("urn:oc:entity:santander") == true) {
                try {
                    uuid = uuid.replace(":", "_");
                    //attribute_id = attribute_id.replace(":", "_");
                    if (attribute_id.startsWith("urn:oc:attributeType:") == true) {
                        attribute_id = attribute_id.replace("urn:oc:attributeType:", "");
                    }
                    if (attribute_id.contains("Bike")) {
                        r = modelService.getSmartCitizenResponse(santanderAPIService.getData(uuid, "urn_oc_entityType_bikeStation", attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                    } else {
                        r = modelService.getSmartCitizenResponse(santanderAPIService.getData(uuid, "urn_oc_entityType_iotdevice", attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                    }
                } catch (Exception e) {
                    throw e;
                }
            } else if (uuid.startsWith("urn:oc:entity:london:smartphone") == true) {
                try {
                    r = modelService.getSmartCitizenResponse2(smartphoneAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                } catch (Exception e) {
                    throw e;
                }
            } else if (uuid.startsWith("urn:oc:entity:patra:smartphone") == true) {
                try {
                    r = modelService.getSmartCitizenResponse2(smartphoneAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                } catch (Exception e) {
                    throw e;
                }
            }  else if (uuid.startsWith("urn:oc:entity:patra") == true) {
                try {
                    r = modelService.getSmartCitizenResponse2(patraAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                } catch (Exception e) {
                    throw e;
                }
            } else if (uuid.startsWith("urn:oc:entity:london:") == true) {
                try {

                    r = londonAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit);
                } catch (Exception e) {
                    throw e;
                }
            }
            if (r.getReadings() == null || r.getReadings().length == 0) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        return r;
    }


}