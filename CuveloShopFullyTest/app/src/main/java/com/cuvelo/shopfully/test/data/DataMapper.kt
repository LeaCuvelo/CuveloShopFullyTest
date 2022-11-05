package com.cuvelo.shopfully.test.data

import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.test.data.db.FlyerDataEntity

fun FlyerDataEntity.toFlyerDataDomain() = FlyerDataDomain(id.toString(), retailerId, title)

fun FlyerDataDomain.toFlyerDataEntity() = FlyerDataEntity(id.toInt(), retailerId, title)