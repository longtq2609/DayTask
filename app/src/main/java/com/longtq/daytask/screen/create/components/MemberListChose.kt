package com.longtq.daytask.screen.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.fordBlue
import com.longtq.daytask.ui.theme.orangeYellow
import com.longtq.daytask.ui.theme.white
import com.longtq.daytask.util.components.AppText
import com.longtq.domain.entity.User

@Composable
fun MemberListChose(
    users: List<User>,
    modifier: Modifier,
    onRemoveMember: (User) -> Unit,
    onAddMember: () -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8))
    ) {
        items(users) { user ->
            MemberChoseItem(user, onRemoveMember = {
                onRemoveMember(user)
            })
        }
        item { // Special 'item' for the button
            AddMemberButton(onClick = onAddMember)
        }
    }
}

@Composable
fun MemberChoseItem(user: User, onRemoveMember: (User) -> Unit) {
    Row(
        modifier = Modifier
            .background(fordBlue)
            .padding(dimensionResource(id = R.dimen.dimen_8))
            .height(dimensionResource(id = R.dimen.dimen_40)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = user.avatar,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.dimen_40))
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_8)))
        AppText(
            text = user.displayName ?: "",
            fontWeight = FontWeight.Medium,
            fontSize = dimensionResource(
                id = R.dimen.dimen_14
            ).value.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {
            onRemoveMember(user)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_closes_quare),
                contentDescription = "Remove",
                tint = white
            )
        }
    }
}

@Composable
fun AddMemberButton(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.dimen_55))
            .background(orangeYellow)

    ) {
        IconButton(onClick = { onClick.invoke() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add member"
            )
        }

    }
}