package com.chenyifaer.back.entity.dto;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * SKU列表 - DTO
 * @Author:wudh
 * @Date: 2019/4/30 13:35
 */
@Data
@Accessors(chain = true)
public class SkuKeyDTO extends PageDTO {

    public interface Add{};

    public interface Update{};

    public interface Delete{};

    /** 主键 */
    @NotNull(groups = {SkuKeyDTO.Delete.class,SkuValueDTO.Update.class} , message = "skuKeyId不能为空")
    private Integer skuKeyId;

    /** valueId集合 */
    @NotNull(groups = {SkuKeyDTO.Add.class} , message = "valueId集合不能为空")
    private List<Integer> valueIdList;

    /** key值 */
    @Length(max = 30 , message = "key值不能超过30个字符")
    @NotNull(groups = {SkuKeyDTO.Add.class} , message = "key值不能为空")
    private String keyName;

    /** value值 */
    @Length(max = 30 , message = "value值不能超过30个字符")
    private String valueName;

    /** 起始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

}
