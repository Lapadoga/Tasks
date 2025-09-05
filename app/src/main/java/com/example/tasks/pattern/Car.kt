package com.example.tasks.pattern

import com.example.tasks.pattern.parts.Engine

class Car private constructor(
    val mark: String,
    val mileage: Int,
    val color: String,
    val engine: Engine?
) {
    class Builder {
        private var mark: String = ""
        private var mileage: Int = 0
        private var color: String = ""
        private var engine: Engine? = null

        fun setMark(mark: String): Builder {
            this.mark = mark
            return this
        }

        fun setMileage(mileage: Int): Builder {
            this.mileage = mileage
            return this
        }

        fun setColor(color: String): Builder {
            this.color = color
            return this
        }

        fun setEngine(engine: Engine?): Builder {
            this.engine = engine
            return this
        }

        fun build(): Car = Car(
            mark = this.mark,
            mileage = this.mileage,
            color = this.color,
            engine = this.engine
        )
    }
}




