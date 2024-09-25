defmodule Sheet do
  @spec sumif(list(integer()), boolean()) :: integer()
  def sumif(xs, y) do
    if y do
      Enum.sum(xs)
    else
      -1
    end
  end

  @spec nummercounter(list(integer()), integer()) :: integer()
  def nummercounter(xs, y) do
    count =
      xs
      |> Enum.filter(fn x -> x == y end)
      |> length()

    count * y
  end

  @spec pasch4checker(list(integer())) :: boolean()
  def pasch4checker(xs) do
    length(Enum.filter(xs, &(&1 = hd(xs)))) >= 4 ||
      length(Enum.filter(xs, fn x -> x == Enum.at(xs, 1) end)) >= 4
  end

  @spec pasch3checker(list(integer())) :: boolean()
  def pasch3checker(xs) do
    Enum.any?(xs, fn x -> length(Enum.filter(xs, fn y -> y == x end)) >= 3 end)
  end

  @spec full_house_check(list(integer())) :: boolean()
  def full_house_check(hand) do
    hand
    |> Enum.sort()
    |> Enum.group_by(& &1)
    |> Map.values()
    |> Enum.map(&length/1)
    |> Enum.sort() ==
      [2, 3]
  end

  @spec grstrcheck(list(integer())) :: boolean()
  def grstrcheck(hand) do
    (length(Enum.uniq(hand)) == 5 && Enum.sort(hand) == [1, 2, 3, 4, 5]) ||
      Enum.sort(hand) == [2, 3, 4, 5, 6]
  end

  @spec klstrcheck(list(integer())) :: boolean()
  def klstrcheck(numbers) when length(numbers) == 5 do
    numbers
    |> Enum.sort()
    |> Enum.uniq()
    |> check_consecutive(4)
  end

  def klstrcheck(_), do: false

  @spec check_consecutive(list(integer()), integer()) :: boolean()
  defp check_consecutive(numbers, count) do
    numbers
    |> Enum.chunk_every(count, 1, :discard)
    |> Enum.any?(fn chunk ->
      chunk == Enum.to_list(Enum.min(chunk)..Enum.max(chunk))
    end)
  end

  @spec kniffelcheck(list(integer())) :: boolean()
  def kniffelcheck(numbers) when is_list(numbers) and length(numbers) == 5 do
    Enum.all?(tl(numbers), fn x -> x == hd(numbers) end)
  end

  def kniffelcheck(_), do: false
end
