package code.name.monkey.retromusic.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.core.view.updateLayoutParams
import code.name.monkey.retromusic.databinding.CollapsingAppbarLayoutBinding
import code.name.monkey.retromusic.databinding.SimpleAppbarLayoutBinding
import code.name.monkey.retromusic.util.PreferenceUtil
import com.afollestad.materialdialogs.utils.MDUtil.updatePadding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
import com.google.android.material.shape.MaterialShapeDrawable
import dev.chrisbanes.insetter.applyInsetter

class TopAppBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1,
) : AppBarLayout(context, attrs, defStyleAttr) {
    private var simpleAppbarBinding: SimpleAppbarLayoutBinding? = null
//    private var collapsingAppbarBinding: CollapsingAppbarLayoutBinding? = null

    val mode: AppBarMode = PreferenceUtil.appBarMode

    init {
//        if (mode == AppBarMode.COLLAPSING) {
//            collapsingAppbarBinding =
//                CollapsingAppbarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
//            val isLandscape =
//                context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
//            if (isLandscape) {
//                fitsSystemWindows = false
//            }
////            collapsingAppbarBinding?.collapsingToolbarLayout?.setPadding(0,
////                StatusBarView.getStatusBarHeight(resources), 0, 0)
//        } else {
            simpleAppbarBinding =
                SimpleAppbarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
            simpleAppbarBinding?.root?.applyInsetter {
                type(navigationBars = true) {
                    padding(horizontal = true)
                }
            }
            simpleAppbarBinding?.toolbarContainer?.setPadding(0, StatusBarView.getStatusBarHeight(resources), 0, 0)
            statusBarForeground = MaterialShapeDrawable.createWithElevationOverlay(context)
//        }
    }

    fun pinWhenScrolled() {
        simpleAppbarBinding?.root?.updateLayoutParams<LayoutParams> {
            scrollFlags = SCROLL_FLAG_NO_SCROLL
        }
    }

    val toolbar: Toolbar
        get() =
//            if (mode == AppBarMode.COLLAPSING) {
//            collapsingAppbarBinding?.toolbar!!
//        } else {
            simpleAppbarBinding?.toolbar!!
//        }

    var title: String
        get() =
//            if (mode == AppBarMode.COLLAPSING) {
//            collapsingAppbarBinding?.collapsingToolbarLayout?.title.toString()
//        } else {
            simpleAppbarBinding?.appNameText?.text.toString()
//        }
        set(value) {
//            if (mode == AppBarMode.COLLAPSING) {
//                collapsingAppbarBinding?.collapsingToolbarLayout?.title = value
//            } else {
                simpleAppbarBinding?.appNameText?.text = value
//            }
        }

    enum class AppBarMode {
        COLLAPSING,
        SIMPLE
    }
}
